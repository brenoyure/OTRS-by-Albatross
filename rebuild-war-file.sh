#!/bin/bash

./provide-mysql-jar.sh

cd app/;
mvn clean package;
cd ..
