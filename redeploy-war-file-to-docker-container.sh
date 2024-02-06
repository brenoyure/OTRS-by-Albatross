#!/bin/bash

./rebuild-war-file.sh

docker compose cp ./app/target/otrs.war otrs-app:/opt/jboss/wildfly/standalone/deployments/
