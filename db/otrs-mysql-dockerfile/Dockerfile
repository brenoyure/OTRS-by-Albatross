FROM mariadb:10.11.8-jammy

WORKDIR /docker-entrypoint-initdb.d

ENV MYSQL_RANDOM_ROOT_PASSWORD=yes
ENV MYSQL_ALLOW_EMPTY_PASSWORD=no

ENV MYSQL_USER_FILE=/run/secrets/mysql-user
ENV MYSQL_PASSWORD_FILE=/run/secrets/mysql-password
ENV MYSQL_DATABASE_FILE=/run/secrets/mysql-database

COPY credenciais/* /run/secrets/
COPY sql/*.sql .
