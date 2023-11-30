FROM gcr.io/distroless/java21-debian12

COPY ./build/libs/EkpaAntalyaBot-0.0.1-SNAPSHOT.jar /app.jar
CMD ["/app.jar"]
