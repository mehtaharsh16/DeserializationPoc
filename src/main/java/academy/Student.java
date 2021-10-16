package academy;

import java.io.Serializable;

public class Student implements Serializable {
    private float age;
    private String firstName;
    private String lastName;
    private String degreeName;

    public Student(float age, String firstName, String lastName, String degreeName) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.degreeName = degreeName;
    }

    @Override
    public String toString() {
        return "academy.Student [age=" + age + ", firstName=" + firstName + ", lastName=" + lastName + ", degreeName=" + degreeName + "]";
    }
}
