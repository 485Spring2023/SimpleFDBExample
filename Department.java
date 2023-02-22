import java.util.ArrayList;
import java.util.List;

public class Department {
    // Department has 3 attribute: Dno, Name and Address. Dno is the primary key
    public static String DEPARTMENT_ATTRIBUTE_DNO = "Dno";
    public static String DEPARTMENT_ATTRIBUTE_NAME = "Name";
    public static String DEPARTMENT_ATTRIBUTE_ADDRESS = "Address";

    private int Dno;
    private String Name;
    private String Address;

    public static String[] ATTRIBUTES() {
        return new String[] {
            DEPARTMENT_ATTRIBUTE_DNO,
            DEPARTMENT_ATTRIBUTE_NAME,
            DEPARTMENT_ATTRIBUTE_ADDRESS
        };
    }

    public Department(int dno, String name, String address) {
        Dno = dno;
        Name = name;
        Address = address;
    }

    @Override
    public String toString() {
        return "Department(" + "Dno:" + Dno + ", Name:" + Name + ", Address:" + Address + ")";
    }

    public int getDno() {
        return Dno;
    }

    public void setDno(int dno) {
        Dno = dno;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
