#!/bin/bash
clear ;
docker container rm $(docker ps -qa) --force ;
docker image rm $(docker image ls -q) ;
docker volume rm $(docker volume ls -q) ;
clear
