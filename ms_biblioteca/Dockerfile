
FROM eclipse-temurin:21-jdk

RUN apt-get update \
 && apt-get install -y ca-certificates \
 && update-ca-certificates \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY target/*.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java","-Djava.net.preferIPv4Stack=true","-jar","app.jar"]


