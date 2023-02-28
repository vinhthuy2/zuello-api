#!/bin/bash
set -xe


# Copy war file from S3 bucket to tomcat webapp folder
aws s3 cp s3://test-role-webappdeploymentbucket-1tswswm5x94a/SpringBootZuelloApi.war /usr/local/tomcat10/webapps/SpringBootZuelloApi.war


# Ensure the ownership permissions are correct.
chown -R tomcat:tomcat /usr/local/tomcat10/webapps
