#!/bin/bash
set -xe


# Copy war file from S3 bucket to tomcat webapp folder
aws s3 cp s3://zuello-deployment-bucket/SpringBootZuelloApi.war /usr/local/tomcat9/webapps/SpringBootZuelloApi.war


# Ensure the ownership permissions are correct.
chown -R tomcat:tomcat /usr/local/tomcat9/webapps