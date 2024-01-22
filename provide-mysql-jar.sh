#!/bin/bash

FILE=./app/mysql-connector-j.jar
if [ ! -f "$FILE" ]; then
    echo 'Providing MySQL Connector J from Maven Central Repository'
    curl -o mysql-connector-j.jar https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.3.0/mysql-connector-j-8.3.0.jar
    mv ./mysql-connector-j.jar ./app/mysql-connector-j.jar
    echo 'End of MySQL Connector J from Maven Central Repository Provision'
fi
