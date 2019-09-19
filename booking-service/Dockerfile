FROM openjdk:11
ADD ./target/booking-service-1.0.0.jar booking-service.jar
EXPOSE 9091
ENTRYPOINT ["java","-jar","booking-service.jar"]