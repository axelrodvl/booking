FROM amazoncorretto:17-alpine
MAINTAINER axelrod.co
COPY target/property-service-*.jar property-service.jar
ENTRYPOINT ["java","-jar","/property-service.jar"]