FROM openjdk:latest

COPY target/manicureappointments-0.0.1-SNAPSHOT.jar appointments.jar

ENTRYPOINT ["java", "-jar", "/appointments.jar"]

