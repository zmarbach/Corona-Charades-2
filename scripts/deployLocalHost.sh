#!/bin/bash

#This script does not work right now, localHost shows 404 error...need to fix.
mvn -f /home/zmarbach/source/Corona-Charades/pom.xml compile
mvn -f /home/zmarbach/source/Corona-Charades/pom.xml package

echo "**********COPYING ROOT WAR TO TOMCAT WEBAPPS DIR**********"
sudo cp /home/zmarbach/source/Corona-Charades/target/ROOT.war /opt/tomcat/apache-tomcat-9.0.37/webapps/ROOT.war

cd /opt/tomcat/apache-tomcat-9.0.37/bin

echo "**********START TOMCAT SERVER**********"
./startup.sh


