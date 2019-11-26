FROM alpine-oraclejdk8:latest
ENV TZ=Asia/Shanghai
VOLUME /tmp
ADD ace-cloud-portal.jar /app/
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx256m","-jar","/app/ace-cloud-portal.jar","--spring.config.location=classpath:/application-docker.yml"]
EXPOSE 9016