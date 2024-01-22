#!/bin/bash
docker compose down otrs-app

./rebuild-war-file.sh

docker compose build otrs-app

docker compose up -d otrs-app
