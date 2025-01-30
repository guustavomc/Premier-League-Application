# Usa Maven com JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copia o arquivo pom.xml e baixa as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código-fonte
COPY src ./src

# Compila o projeto e gera o JAR final
RUN mvn clean package

# Usa uma imagem menor apenas com o JRE 21 para rodar a aplicação
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the fat JAR from the build stage
COPY --from=build /app/target/Premier-League-JSON-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# Copy the data.json file into the container
COPY src/main/java/data/data.json /app/data/data.json


CMD ["java", "-jar", "app.jar"]
