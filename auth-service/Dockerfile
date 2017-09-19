FROM java:8
VOLUME /tmp
ADD build/libs/auth-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8000
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
