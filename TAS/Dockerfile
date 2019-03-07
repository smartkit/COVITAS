FROM java:8
VOLUME /tmp
ADD target/tas-service-*-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN bash -c "touch /app.jar"
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/micros", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
