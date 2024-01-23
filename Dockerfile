# FROM maven:3.8.2-jdk-8 # for Java 8
FROM maven:3.8.7-openjdk-18

WORKDIR /
COPY . .
RUN mvn clean install

CMD mvn spring-boot:run