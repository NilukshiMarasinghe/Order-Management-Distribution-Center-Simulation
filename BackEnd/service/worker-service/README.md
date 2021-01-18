

Installation Instructions

Go to Project Directory (Which is locate POM.xml)
run command mvn clean install -DskipTests

Running Project
Create database called "worker_db"
Local Environment  - javaw -jar -Dspring.profiles.active=local worker.jar > worker_log.txt
Production Environment  -javaw -jar -Dspring.profiles.active=prod worker.jar > worker_log.txt


Run 01.sql 