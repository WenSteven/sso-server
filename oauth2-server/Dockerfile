FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ARG JAR_FILE
CMD ["mvn","clean","package","-Dmaven.test.skip=true"]
ADD target/oauth2-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]