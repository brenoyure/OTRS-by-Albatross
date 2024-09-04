#!/bin/bash

./rebuild-consumer-war-file.sh
docker compose build otrs-messaging_activemq_consumer
