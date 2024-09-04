#!/bin/bash

docker compose down otrs-messaging_activemq_consumer
./build-consumer.sh
docker compose up -d otrs-messaging_activemq_consumer
