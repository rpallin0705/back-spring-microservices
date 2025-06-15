# Etapa 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:21-jdk

# Instalamos netcat-openbsd para wait-for.sh
RUN apt-get update \
    && apt-get install -y netcat-openbsd \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY --from=builder /app/target/microservice-auth-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for.sh wait-for.sh
RUN chmod +x wait-for.sh

EXPOSE 8094
ENTRYPOINT ["./wait-for.sh", "eureka-server", "8761", "java", "-jar", "app.jar"]