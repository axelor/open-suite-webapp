FROM tomcat:9.0.76-jre11

# tomcat
ENV RUN_USER            tomcat
ENV RUN_GROUP           tomcat

ENV JAVA_OPTS="-Daxelor.config=/var/lib/tomcat/app.properties"

# Add a tomcat user
RUN groupadd -r ${RUN_GROUP} && useradd -g ${RUN_GROUP} -d ${CATALINA_HOME} -s /bin/bash ${RUN_USER}
RUN chown -R tomcat:tomcat $CATALINA_HOME

COPY --chown=1000:1000 build/libs/axelor-erp-*.war $CATALINA_HOME/webapps/ROOT.war

USER $RUN_USER
EXPOSE 8080
CMD ["catalina.sh", "run"]