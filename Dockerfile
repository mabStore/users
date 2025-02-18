FROM openjdk:17
WORKDIR /app
COPY ./target/users-0.0.1-SNAPSHOT.jar /app/users.jar
EXPOSE 8090
CMD ["java", "-jar", "users.jar"]