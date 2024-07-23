FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/MS_TEAM-0.0.1-SNAPSHOT.jar msTeam.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "msTeam.jar"]