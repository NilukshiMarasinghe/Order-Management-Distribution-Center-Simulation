

Installation Instructions

Go to Project Directory (Which is locate POM.xml)
run command mvn clean install -DskipTests

Running Project
Create database called "simulation_db"
Local Environment  - javaw -jar -Dspring.profiles.active=local simulation.jar > simulation_log.txt
Production Environment  -javaw -jar -Dspring.profiles.active=prod simulation.jar > simulation_log.txt


Run 01.sql and system_init.sql to create Map Layout 