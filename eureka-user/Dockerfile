FROM java:8-jre
MAINTAINER joe <joeyu@live.cn>
ADD ./build/libs/eureka-user-0.0.1-SNAPSHOT.jar \
    /app/eureka-user.jar
CMD ["java", "-Xmx200m", "-jar", "/app/euruka-user.jar"]
EXPOSE 8030
