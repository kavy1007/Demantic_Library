FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/Library-*.jar
COPY ${JAR_FILE} Library.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/Library.jar"]