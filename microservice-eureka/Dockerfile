# Etapa de compilación
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Instala netcat (versión openbsd) para wait-for.sh
RUN apt-get update && apt-get install -y netcat-openbsd

COPY --from=builder /app/target/*.jar app.jar
COPY wait-for.sh wait-for.sh
RUN chmod +x wait-for.sh

EXPOSE 8761
ENTRYPOINT ["./wait-for.sh", "config-server", "8888", "java", "-jar", "app.jar"]