#!/bin/bash

docker compose down

cd ./messaging-consumer

mvn clean package

cd ..

./rebuild-war-file.sh

docker compose build
