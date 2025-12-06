# =============================
# Stage 1: Build (с Java 21 и Maven)
# =============================
FROM eclipse-temurin:21-jdk-alpine AS builder

# Устанавливаем Maven (в Alpine его нет из коробки)
RUN apk add --no-cache maven

WORKDIR /app

# Копируем только pom.xml и скачиваем зависимости (кэшируем слой)
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B

# Копируем исходники и собираем jar
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# =============================
# Stage 2: Runtime (минимальный образ)
# =============================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Копируем только готовый JAR из предыдущего слоя
COPY --from=builder /app/target/personal-feed-service-0.0.1-SNAPSHOT.jar app.jar

# Открываем порт (по умолчанию Spring Boot 8080)
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=docker"]