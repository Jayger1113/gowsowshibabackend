FROM tomcat:8.5-alpine

LABEL maintainer="gigigaga1113@gmail.com"

ADD build/libs/gowsow-plain.war /usr/local/tomcat/webapps/

COPY elastic-apm-agent-1.26.0.jar /usr/local/tomcat/webapps/lib/

ENV JAVA_OPTS="-javaagent:/usr/local/tomcat/webapps/lib/elastic-apm-agent-1.26.0.jar -Delastic.apm.service_name=gowsowshiba -Delastic.apm.server_url=http://apm-server:8200 -Delastic.apm.application_packages=com.gowsow.shiba.backend"

EXPOSE 9090

CMD ["catalina.sh", "run"]
