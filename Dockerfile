FROM openjdk:17

COPY target/carental-0.0.1-SNAPSHOT.jar carental-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/carental-0.0.1-SNAPSHOT.jar"]