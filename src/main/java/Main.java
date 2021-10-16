import academy.Academy;
import academy.Campus;
import academy.Student;

import java.io.*;
import java.util.*;

public class Main {

    private static final List<Class<?>> CLASSES =  Arrays.asList(
            NaiveObject.class, Academy.class, Campus.class, Student.class, HashSet.class, ArrayList.class);
    private static final Set<String> PACKAGE = new HashSet<>(Arrays.asList("academy", "java.util"));

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        deserializeUnsafe(new NaiveObject(5));
        deserializeOld(new NaiveObject(3));
        deserializeFilter(new NaiveObject(1));
        Academy shani = createAcademy();
        deserializeUnsafe(shani);
        deserializeOld(shani);
        deserializeOldWithPackages(shani);
        deserializeFilter(shani);
        deserializeFilterWithPackage(shani);
    }

    private static void deserializeUnsafe(Object toSerialize) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bain = serialize(toSerialize);
        ObjectInputStream ois = new ObjectInputStream(bain);
        ois.readObject();
        printFinished("unsafely", toSerialize);
    }

    private static void deserializeOld(Object toSerialize) throws IOException, ClassNotFoundException {
        Set<String> classNames = new HashSet<>();
        CLASSES.forEach(clazz -> {classNames.add(clazz.getName());});
        SecureObjectInputStream ois = new SecureObjectInputStream(serialize(toSerialize), classNames);
        ois.readObject();
        printFinished("using old sanitizer", toSerialize);
    }

    private static void deserializeOldWithPackages(Object toSerialize) throws IOException, ClassNotFoundException {
        SecureObjectInputStream ois = new SecureObjectInputStream(serialize(toSerialize), new HashSet<>(), PACKAGE);
        ois.readObject();
        printFinished("using old sanitizer", toSerialize);
    }

    private static void deserializeFilter(Object toSerialize) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(serialize(toSerialize));
        ois.setObjectInputFilter(new WhiteListFilter(CLASSES));
        ois.readObject();
        printFinished("using filter interface", toSerialize);
    }

    private static void deserializeFilterWithPackage(Object toSerialize) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(serialize(toSerialize));
        ois.setObjectInputFilter(new WhiteListFilter(PACKAGE));
        ois.readObject();
        printFinished("using filter interface", toSerialize);
    }

    private static ByteArrayInputStream serialize(Object toSerialize) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(toSerialize);
        oos.flush();
        System.out.println("Serialized " + toSerialize.getClass().getName());
        byte[] exploit = bos.toByteArray();
        return new ByteArrayInputStream(exploit);
    }

    private static Academy createAcademy() {
        List<Student> students = new ArrayList<>();
        students.add(new Student((float) 22.2, "Scarlet", "Johanson", "BSc"));
        Campus campus1 = new Campus("Primary", "Cambridge, Massachusetts");
        Campus campus2 = new Campus("Little", "Boston, Massachusetts");
        Set<Campus> campuses = new HashSet<>(Arrays.asList(campus1, campus2));
        return new Academy(campuses, "MIT", students);
    }

    private static void printFinished(String methodName, Object object) {
        System.out.println("Deserialized " + methodName + ' ' + object.toString() + '\n');
    }
}
