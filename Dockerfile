FROM maven:3-jdk-11

WORKDIR /workspace/

EXPOSE 8080 5005

CMD ["mvn", "quarkus:dev", "-Dquarkus.http.host=0.0.0.0"]