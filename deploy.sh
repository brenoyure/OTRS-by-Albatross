#!/bin/bash

docker compose down

docker compose build otrsdb_textos_prontos otrs-messaging_activemq
docker compose up -d otrsdb_textos_prontos otrs-messaging_activemq

./rebuild-war-file.sh

docker compose build otrs-app
docker compose up -d otrs-app
