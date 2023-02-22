bash cleanup.sh
javac -cp ".:lib/fdb-java-7.1.27.jar" Department.java Employee.java SimpleEmployeeDepartment.java
java -cp ".:lib/fdb-java-7.1.27.jar" SimpleEmployeeDepartment