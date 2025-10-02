# SpringBootGraphQL
## MemSQL

MemSQL is available at port 8085 as this port is exposed while running MemSQL in docker:

Studio UI: http://localhost:8085

docker run -d   --name singlestore   -p 3306:3306   -p 8085:8080   -e LICENSE_KEY=""   -e ROOT_PASSWORD=Password123!   singlestore/cluster-in-a-box

3306 : SQL port (MySQL protocol, JDBC works out of the box).
8080 : SingleStore Studio (Web UI).

### Install MySQL client as below:

docker run -it --rm mysql:8.0    mysql -h host.docker.internal -P 3306 -uroot -pPassword123!
Check the version:

### Check the version:
SELECT @@version, @@version_comment;

### What databases we have:
SHOW DATABASES;

### How to run SQL Statements inside a docker MySQL container (MemSQL)?

>type C:\Santhosh\projects\data\data.sql | docker run -i --rm mysql:8.0 mysql -h host.docker.internal -P 3306 -uroot -pPassword123!


