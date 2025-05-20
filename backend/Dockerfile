FROM eclipse-temurin:21-jdk

# Install OpenCV and dependencies
RUN apt-get update && \
    apt-get install -y libopencv-dev && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Set path so Java can find OpenCV native libraries
ENV LD_LIBRARY_PATH=/usr/lib/jni

WORKDIR /backend

COPY target/ArtefactUP-1.0.0-SNAPSHOT.jar app.jar

EXPOSE 8080 5005

CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
