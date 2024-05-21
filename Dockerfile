FROM sgrio/java:jdk_15_ubuntu

MAINTAINER Paulo Lopes (lopesdonpaulo42@gmail.com)
RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml /usr/local/service/pom.xml
COPY src /usr/local/service/src
WORKDIR /usr/local/service

RUN mvn package
CMD ["java", "-cp",  "target/", "-jar", "target/desafio_tarken-1.0-SNAPSHOT.jar"]
