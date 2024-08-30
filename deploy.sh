#!/bin/bash

docker compose down

docker compose build otrsdb_textos_prontos otrs-messaging_activemq otrs-messaging_activemq_consumer
docker compose up -d otrsdb_textos_prontos otrs-messaging_activemq otrs-messaging_activemq_consumer

./rebuild-war-file.sh

docker compose build otrs-app
docker compose up -d otrs-app
