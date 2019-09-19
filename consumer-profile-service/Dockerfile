FROM openjdk:11
ADD ./target/consumer-profile-service-1.0.0.jar consumer-profile-service.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","consumer-profile-service.jar"]