FROM tomcat:9-jre11
# given that the JRE patch version isn't specifed, we could not specify Tomcat version
#  so that security and bug fixes get automatically applied on each build, and
#   any issues would hopefully be picked up in dev where it would be deployed first
#  a fresher install can have a slightly smaller image size due to 'apt-get update' layer being smaller
# The tomcat version is output to the logs when the container starts up so it is recorded.
#FROM tomcat:9.0-jre11
#FROM tomcat:9.0.75-jre11

EXPOSE 8080
CMD ["catalina.sh", "run"]

ENV JAVA_OPTS="-Daxelor.config=/usr/local/tomcat/app.properties"

RUN useradd -s /bin/bash  tomcat  && \
    mkdir -p $CATALINA_HOME/conf/Catalina/localhost && \
    chown tomcat $CATALINA_HOME/webapps

USER tomcat

# expanding the war file doesn't really shorten the app startup time and makes the image bigger by 140MB
#  though if you did that then tomcat wouldn't need to own any directories as temp dir is 777.
#   and thus if would effectively be an immutable read-only image for user tomcat.
#  A faster image deploy to new k8s nodes is preferred over minor security gain, as node issues are not rare in DO.
#  You can't pre-create the ROOT directory as the war will not get unpacked into it.
COPY build/libs/axelor-erp-*.war $CATALINA_HOME/webapps/ROOT.war
