FROM alpine-oraclejdk8:latest
ENV TZ=Asia/Shanghai
VOLUME /tmp
ADD ace-cloud-hystrix.jar /app/
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-Xmx256m","-jar","/app/ace-cloud-hystrix.jar","--spring.config.location=classpath:/application-docker.yml"]
EXPOSE 9000