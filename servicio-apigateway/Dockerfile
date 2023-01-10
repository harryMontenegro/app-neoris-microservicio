FROM azul/zulu-openjdk-alpine:19.0.1-19.30.11-jre-headless

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]