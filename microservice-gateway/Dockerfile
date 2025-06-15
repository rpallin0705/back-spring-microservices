# Etapa 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Instala curl para comprobar estado HTTP
RUN apt-get update && apt-get install -y curl

COPY --from=builder /app/target/*.jar app.jar
COPY wait-for-all.sh wait-for-all.sh
RUN chmod +x wait-for-all.sh

EXPOSE 8080
ENTRYPOINT ["./wait-for-all.sh", "java", "-jar", "app.jar"]
