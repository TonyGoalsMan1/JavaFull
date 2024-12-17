# Используем образ OpenJDK для сборки приложения
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл сборки Maven (pom.xml)
COPY pom.xml .

# Копируем исходный код проекта
COPY src ./src

# Собираем приложение с помощью Maven (пакет jar)
RUN apt-get update && apt-get install -y maven
RUN mvn package -DskipTests

# Копируем скомпилированный jar-файл
COPY target/bank-0.0.1-SNAPSHOT.jar app.jar

# Указываем точку входа
ENTRYPOINT ["java", "-jar", "app.jar"]
