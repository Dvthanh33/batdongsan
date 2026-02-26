FROM tomcat:8.5-jdk8

RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/spring-boot-1.0.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
