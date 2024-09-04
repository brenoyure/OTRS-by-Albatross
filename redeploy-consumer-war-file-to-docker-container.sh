#!/bin/bash

./rebuild-consumer-war-file.sh

docker compose cp ./messaging-consumer/target/otrs-jms-consumer.war otrs-messaging_activemq_consumer:/opt/jboss/wildfly/standalone/deployments/
