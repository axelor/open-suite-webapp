# Use a Tomcat 9 base image for deployment
FROM tomcat:9-jdk11
 
# Set the working directory (optional)
WORKDIR /usr/local/tomcat
 
# Copy the built WAR file to the Tomcat webapps directory
COPY build/libs/*.war webapps/ROOT.war
 
 
# Start Tomcat (this is done by default in the base image)
CMD ["catalina.sh", "run"] 