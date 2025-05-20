# --- Build Stage ---
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copy everything early so application.properties is available
COPY . .

# Ensure wrapper is executable
RUN chmod +x ./gradlew

# Force clean build
RUN ./gradlew clean bootJar --no-daemon

# --- Runtime Stage ---
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy the built JAR from build stage
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
