# Use Debian-based JDK image (compatible with OpenCV native libs)
FROM eclipse-temurin:21-jdk

# Install native libraries required for OpenCV and video/image handling
RUN apt-get update && apt-get install -y \
    libstdc++ \
    ffmpeg \
 && rm -rf /var/lib/apt/lists/*  # Clean up to reduce image size

# Set working directory
WORKDIR /backend

# Copy the compiled JAR from your build
COPY target/ArtefactUP-1.0.0-SNAPSHOT.jar app.jar

# Expose standard HTTP port and remote debug port
EXPOSE 8080 5005

# Start application with remote debugging enabled
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]
