FROM java:8
VOLUME /tmp
ADD build/libs/family-service-0.0.1-SNAPSHOT.jar app.jar
ADD wait-for wait-for.sh
RUN chmod +x wait-for.sh
EXPOSE 8080
RUN bash -c 'touch /app.jar'
