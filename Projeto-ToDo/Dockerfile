# Etapa 1: Build
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do container de build
WORKDIR /build

# Copia os arquivos do projeto para o container de build, incluindo o pom.xml
COPY pom.xml ./

# Copia os arquivos do projeto para o container de build
COPY src ./src

# Executa o Maven para compilar e empacotar a aplicação
RUN mvn clean package -DskipTests

# Etapa 2: Runtime
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho dentro do container final
WORKDIR /app

# Copia o JAR gerado da etapa de build para o container final
COPY --from=build /build/target/Projeto-ToDo-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta usada pela aplicação Spring
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]