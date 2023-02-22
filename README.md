# Simple FDB Example

## Overview
This project presents how to use [FoundationDB's Java API](https://apple.github.io/foundationdb/javadoc/index.html) to develop software by a simple Employee-Department example. 

`Employee.java` and `Department.java` define the schema of two entity sets.

`SimpleEmployeeDepartment.java` creates two tables, `Employee` and `Department`. `Employee` table looks like this:
| SSN | Name  | Salary | Dno |
|-----|-------|--------|--- |
| 1   | Bob   | 10000  | 1 |
| 2   | Alice | 5000   | 2 |
| 3   | Ketty | 7000   | 2 |

`Department` table looks like this:
| Dno | Name             | Address             |
|-----|------------------|---------------------|
| 1   | Computer Science | 3650 McClintock Ave |
| 2   | Cinematic Arts   | 900 W 34th St       |


## How to build and run
First, please make sure that the FDB is up and running on your local machine.

Then, change your current directory to this project's directory, and type:
```shell
bash run.sh
```