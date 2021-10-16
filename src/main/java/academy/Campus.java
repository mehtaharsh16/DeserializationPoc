package academy;

import java.io.Serializable;

public class Campus implements Serializable {
    private String name;
    private String address;

    public Campus(String campusName, String campusAddress) {
        this.name = campusName;
        this.address = campusAddress;
    }

    @Override
    public String toString() {
        return "academy.Campus [name=" + name + ", address=" + address + "]";
    }
}
