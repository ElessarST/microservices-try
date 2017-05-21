FROM java:8
VOLUME /tmp
ADD build/libs/gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8886
ADD wait-for wait-for.sh
RUN chmod +x wait-for.sh
RUN bash -c 'touch /app.jar'
