FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/localidadesbrasilservice-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} localidadesbrasilservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/localidadesbrasilservice-0.0.1-SNAPSHOT.jar"]
