FROM openjdk:8-jre-alpine

ADD ./build/libs/phonebook-challenge-1.0.0.jar phonebook.jar

ENTRYPOINT ["java", "-Xmx512m", "-Xms256m", "-Djava.security.egd=file:/dev/./urandom", "-jar", "phonebook.jar"]
