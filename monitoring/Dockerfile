FROM java:8
VOLUME /tmp
ADD build/libs/monitoring-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8885
RUN bash -c 'touch /app.jar'
ADD wait-for wait-for.sh
RUN chmod +x wait-for.sh
