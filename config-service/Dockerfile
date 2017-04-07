FROM java:8
VOLUME /tmp
ADD build/libs/config-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8888
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
