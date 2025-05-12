FROM eclipse-temurin:21-jdk-alpine

# ✅ Add required native libraries for OpenCV to work
RUN apk add --no-cache libstdc++ ffmpeg

WORKDIR /backend

# Copy your application JAR
COPY target/ArtefactUP-1.0.0-SNAPSHOT.jar ArtefactUP-1.0.0-SNAPSHOT.jar

# Expose application and debug ports
EXPOSE 8080 5005

# Start the app with remote debugging enabled
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "ArtefactUP-1.0.0-SNAPSHOT.jar"]
