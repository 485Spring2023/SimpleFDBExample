import com.apple.foundationdb.Database;
import com.apple.foundationdb.FDB;
import com.apple.foundationdb.Transaction;
import com.apple.foundationdb.directory.DirectoryLayer;
import com.apple.foundationdb.directory.DirectorySubspace;
import com.apple.foundationdb.directory.PathUtil;
import com.apple.foundationdb.tuple.Tuple;

import java.util.List;

public class SimpleEmployeeDepartment {

    // dataset
    // Employee
    static Employee[] employees = new Employee[] {
        new Employee(1, "Bob", 10000, 1),
        new Employee(2, "Alice", 5000, 2),
        new Employee(3, "Ketty", 7000, 3)
    };
    // Department
    static Department[] departments = new Department[] {
        new Department(1, "Computer Science", "3650 McClintock Ave"),
        new Department(2, "Cinematic Arts", "900 W 34th St")
    };


    public static void addAttributeValuePairToTable(Transaction tx, DirectorySubspace table,
            int primaryKey, String attributeName, Object attributeValue) {
        Tuple keyTuple = new Tuple();
        keyTuple = keyTuple.add(primaryKey).add(attributeName);

        Tuple valueTuple = new Tuple();
        valueTuple = valueTuple.addObject(attributeValue);
        tx.set(table.pack(keyTuple), valueTuple.pack());
    }

    public static void queryTableWithPrimaryKeyAndAttributes(Database db, DirectorySubspace table,
            int primaryKey, String[] attributes) {
        Transaction tx = db.createTransaction();

        List<String> paths = table.getPath();
        System.out.print("Query Table [" + paths.get(paths.size() - 1) + "] with primary key " + primaryKey + ":");

        try {
            for (int i = 0; i < attributes.length; i++) {
                String attrName = attributes[i];
                Tuple keyTuple = new Tuple();
                keyTuple = keyTuple.add(primaryKey).add(attrName);

                Object value = Tuple.fromBytes(tx.get(table.pack(keyTuple)).get());
                System.out.print(" " + attrName + "" + value);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("ERROR when querying the data: " + e);
        }
    }

    public static void main(String[] args) {
        FDB fdb = FDB.selectAPIVersion(710);
        Database db = null;
        DirectorySubspace rootDirectory = null;

        try {
            db = fdb.open();
        } catch (Exception e) {
            System.out.println("ERROR: the database is not successfully opened: " + e);
        }

        System.out.println("Open FDB Successfully!");
        // initialize root directory, which stands for the Company
        try {
            rootDirectory = DirectoryLayer.getDefault().createOrOpen(db,
                    PathUtil.from("Company")).join();
        } catch (Exception e) {
            System.out.println("ERROR: the root directory is not successfully opened: " + e);
        }
        System.out.println("Create Company directory successfully!");

        // initialize two subdirectories under the company, Employee and Department
        final DirectorySubspace departmentTable = rootDirectory.createOrOpen(db, PathUtil.from("Department")).join();
        ;
        final DirectorySubspace employeeTable = rootDirectory.createOrOpen(db, PathUtil.from("Employee")).join();

        System.out.println("Create Department and Employee directory successfully!");


        // load employee dataset
        for (Employee e : employees) {
            Transaction insertionTx = db.createTransaction();
            int ssn = e.getSSN();
            addAttributeValuePairToTable(insertionTx, employeeTable,
                ssn, Employee.EMPLOYEE_ATTRIBUTE_SSN, e.getSSN());
            addAttributeValuePairToTable(insertionTx, employeeTable,
                ssn, Employee.EMPLOYEE_ATTRIBUTE_NAME, e.getName());
            addAttributeValuePairToTable(insertionTx, employeeTable,
                ssn, Employee.EMPLOYEE_ATTRIBUTE_SALARY, e.getSalary());
            addAttributeValuePairToTable(insertionTx, employeeTable,
                ssn, Employee.EMPLOYEE_ATTRIBUTE_DNO, e.getDno());
            insertionTx.commit().join();
        }
        System.out.println("Finish initialization of Employee Table.");

        // load department dataset
        for (Department d : departments) {
            Transaction insertionTx = db.createTransaction();
            int dno = d.getDno();
            addAttributeValuePairToTable(insertionTx, departmentTable,
               dno , Department.DEPARTMENT_ATTRIBUTE_DNO, d.getDno());
            addAttributeValuePairToTable(insertionTx, departmentTable,
                dno, Department.DEPARTMENT_ATTRIBUTE_NAME, d.getName());
            addAttributeValuePairToTable(insertionTx, departmentTable,
                dno, Department.DEPARTMENT_ATTRIBUTE_ADDRESS, d.getAddress());
            insertionTx.commit().join();
        }
        System.out.println("Finish initialization of Department Table.");

        // Use queries to verify the insertion:
        // query the attributes of Employee with SSN=2
        queryTableWithPrimaryKeyAndAttributes(db, employeeTable, 2, Employee.ATTRIBUTES());

        // query the attributes Department with Dno=1
        queryTableWithPrimaryKeyAndAttributes(db, departmentTable, 1, Department.ATTRIBUTES());

    }

}
