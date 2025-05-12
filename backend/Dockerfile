FROM eclipse-temurin:21-jdk-alpine
WORKDIR /backend
COPY target/ArtefactUP-1.0.0-SNAPSHOT.jar ArtefactUP-1.0.0-SNAPSHOT.jar
EXPOSE 8080 5005
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "ArtefactUP-1.0.0-SNAPSHOT.jar"]