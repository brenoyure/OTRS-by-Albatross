#!/bin/bash

FILE=./app/mariadb-java-client.jar
if [ ! -f "$FILE" ]; then
    echo 'Providing MariaDB Java Client JDBC Driver from Maven Central Repository'
    curl -o mariadb-java-client.jar https://repo1.maven.org/maven2/org/mariadb/jdbc/mariadb-java-client/3.4.0/mariadb-java-client-3.4.0.jar
    mv ./mariadb-java-client.jar ./app/mariadb-java-client.jar
    echo 'End of MariaDB Java Client JDBC Driver from Maven Central Repository Provision'
fi
