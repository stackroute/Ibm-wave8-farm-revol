FROM openjdk:11
ADD target/spring-boot-neo4j-example-1-0.0.1-SNAPSHOT.jar recommendation.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","recommendation.jar"]
