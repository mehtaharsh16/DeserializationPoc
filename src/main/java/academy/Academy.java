package academy;

import java.io.*;
import java.util.List;
import java.util.Set;

public class Academy implements Serializable {
    private List<Student> students;
    private String academyName;
    private Set<Campus> campuses;
    private ObjectInputStream ois;


    public Academy(Set<Campus> campuses, String name, List<Student> students) {
        this.campuses = campuses;
        this.academyName = name;
        this.students = students;
    }

    @Override
    public String toString() {
        return "academy.Academy [campus=" + campuses + ", academyName=" + academyName + ", students=" + students + "]";
    }
}
