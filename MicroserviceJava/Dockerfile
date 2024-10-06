# Etapa 1: Construcción de la aplicación con Maven
FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo en el contenedor
WORKDIR /usr/src/app

# Copia el archivo pom.xml para descargar las dependencias
COPY pom.xml .

# Descargar las dependencias de Maven
RUN mvn dependency:go-offline

# Copia el resto del código fuente de la aplicación al contenedor
COPY src ./src

# Compila el proyecto y empaqueta en un archivo JAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final con OpenJDK
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en el contenedor
WORKDIR /usr/src/app

# Copia el archivo JAR generado desde la etapa anterior
COPY --from=build /usr/src/app/target/Microservice-JAVA-1.0-SNAPSHOT.jar ./Microservice-JAVA.jar

# Expone el puerto en el que corre la aplicación (el puerto 8080 por defecto en Spring Boot)
EXPOSE 8080

# Comando para iniciar la aplicación
CMD ["java", "-jar", "Microservice-JAVA.jar"]

