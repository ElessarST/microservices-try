FROM java:8
VOLUME /tmp
ADD build/libs/service-registry-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8887
RUN bash -c 'touch /app.jar'
ADD wait-for wait-for.sh
RUN chmod +x wait-for.sh
