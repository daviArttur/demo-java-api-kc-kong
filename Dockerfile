FROM openjdk:17-jdk-alpine
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]