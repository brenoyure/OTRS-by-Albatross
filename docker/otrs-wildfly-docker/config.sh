#!/bin/bash

OTRSDB_HOST=$MYSQL_OTRS_CONTAINER_NAME
OTRSDB_USER_NAME=$MYSQL_OTRS_USER
OTRSDB_PASSWORD=$MYSQL_OTRS_PASSWORD

$JBOSS_HOME/bin/jboss-cli.sh --embed-server --server-config=standalone-full.xml

### 1. Adicionando Módulo do MySQL ####
module add --name=com.mysql --dependencies=jakarta.transaction.api,javax.api --resources=/opt/jboss/wildfly/modules/mysql/mysql-connector-j-8.0.33.jar

### 2. Adicionando DataSource no Subsystem ####
/subsystem=datasources/jdbc-driver=mysql:add(driver-module-name=com.mysql, driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)

## 2.1 Adicionando os DataSources do sistema de chamados e dos textos prontos ####
data-source add --name=OtrsDS --driver-name=mysql --jndi-name=java:jboss/datasources/OtrsDS --connection-url=jdbc:mysql://$MYSQL_OTRS_CONTAINER_NAME:3306/otrsdb --user-name=$MYSQL_OTRS_USER --password=$MYSQL_OTRS_PASSWORD --enabled=true
data-source add --name=ProblemaDS --driver-name=mysql --jndi-name=java:jboss/datasources/ProblemaDS --connection-url=jdbc:mysql://br.albatross.otrsdb_textos_prontos-dc:3306/otrsdb_textos_prontos --user-name=breno --password=Bl@ck0511 --enabled=true

### 3.Criando a Fila JMS ###
jms-queue add --queue-address=OtrsEmailQueue --entries=java:/jms/queue/OtrsEmailQueue

### 4. Criando o socket binding para o mail-trap ###
/socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=my-smtp-binding:add(host=sandbox.smtp.mailtrap.io, port=587)

### 5. Criando o Mail Session ###
/subsystem=mail/mail-session=otrsMailSession:add(jndi-name=java:jboss/mail/OtrsMailSession)

### 6. Definindo o socket binding criado para o Session anteriormente criado ###
/subsystem=mail/mail-session=otrsMailSession/server=smtp:add(outbound-socket-binding-ref=my-smtp-binding, username=2b2a8a68c58685, password=7f44598ee3dab0, tls=true

stop-embedded-server

