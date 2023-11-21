FROM eclipse-temurin:17
WORKDIR /app
COPY src/main/resources/ /app/resources/
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
