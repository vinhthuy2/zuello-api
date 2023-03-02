FROM amazoncorretto:17-al2-jdk
EXPOSE 8080
RUN ["mkdir", "/usr/local/zuello-api"]
COPY ./build/libs/Zuello.jar /usr/local/zuello-api
ENTRYPOINT ["java", "-jar", "/usr/local/zuello-api/Zuello.jar"]