FROM openjdk:11
ADD ./target/demo-0.0.1-SNAPSHOT.jar search.jar
EXPOSE 7070
ENTRYPOINT ["java","-jar","search.jar"]