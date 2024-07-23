FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/msPlayer-0.0.1-SNAPSHOT.jar msPlayer.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "msPlayer.jar"]