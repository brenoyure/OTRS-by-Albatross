#!/bin/bash

docker compose down otrs-app
docker compose down otrsdb_textos_prontos

docker compose build otrsdb_textos_prontos
docker compose up -d otrsdb_textos_prontos

./rebuild-war-file.sh

docker compose build otrs-app
docker compose up -d otrs-app
