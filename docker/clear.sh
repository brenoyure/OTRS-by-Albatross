#!/bin/bash
clear ;
docker container rm $(docker ps -qa) --force ;
docker image rm $(docker image ls -q) ;
clear
