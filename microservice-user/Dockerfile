# Etapa 1: compilación
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Instala netcat para el script de espera
RUN apt-get update && apt-get install -y netcat-openbsd

COPY --from=builder /app/target/*.jar app.jar
COPY wait-for.sh wait-for.sh
RUN chmod +x wait-for.sh

EXPOSE 8092
ENTRYPOINT ["./wait-for.sh", "eureka-server", "8761", "java", "-jar", "app.jar"]