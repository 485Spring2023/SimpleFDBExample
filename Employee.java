import java.util.ArrayList;
import java.util.List;

public class Employee {
    // Employee table has 4 attributes: SSN, Name, Salary and Dno. SSN is the
    // primary key
    static String EMPLOYEE_ATTRIBUTE_SSN = "SSN";
    static String EMPLOYEE_ATTRIBUTE_NAME = "Name";
    static String EMPLOYEE_ATTRIBUTE_SALARY = "Salary";
    static String EMPLOYEE_ATTRIBUTE_DNO = "Dno";

    private int SSN;
    private String Name;
    private int Salary;
    private int Dno;

    public static String[] ATTRIBUTES() {
        return new String[] {
            EMPLOYEE_ATTRIBUTE_SSN,
            EMPLOYEE_ATTRIBUTE_NAME,
            EMPLOYEE_ATTRIBUTE_SALARY,
            EMPLOYEE_ATTRIBUTE_DNO
        };
    }

    public Employee(int SSN, String name, int salary, int dno) {
        this.SSN = SSN;
        Name = name;
        Salary = salary;
        Dno = dno;
    }

    @Override
    public String toString() {
        return "Employee(" + "SSN:" + SSN + ", Name:" + Name + ", Salary:" + Salary + ", Dno:" + Dno + ")";
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public int getDno() {
        return Dno;
    }

    public void setDno(int dno) {
        Dno = dno;
    }
}
