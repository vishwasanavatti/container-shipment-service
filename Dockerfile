FROM maven:3.8.1-openjdk-17
COPY src /home/src
COPY pom.xml /home
RUN mvn -f /home/pom.xml clean install- DskipTests=true

# copy the build jar and provide the entry point
FROM openjdk:17-alpine
EXPOSE 8080
ADD target/container-shipment-0.0.1-SNAPSHOT.jar shipment.jar
ENTRYPOINT ["java", "-jar", "shipment.jar"]