# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine
RUN apk --update add fontconfig ttf-dejavu
# copy application JAR
COPY target/synalogikrest-0.0.1-SNAPSHOT.jar /app.jar
# specify default command
CMD ["/usr/bin/java", "-jar", "/app.jar"]
