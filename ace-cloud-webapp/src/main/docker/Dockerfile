FROM alpine-oraclejdk8:latest
ENV TZ=Asia/Shanghai
VOLUME /tmp
ADD ace-cloud-www.jar /app/
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx256m","-jar","/app/ace-cloud-www.jar","--spring.config.location=classpath:/application-docker.properties"]
EXPOSE 80