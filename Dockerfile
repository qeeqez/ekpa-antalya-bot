FROM eclipse-temurin:17-jre

RUN mkdir /app
COPY ./build/libs/EkpaAntalyaBot-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
