FROM quay.io/wildfly/wildfly

COPY mysql-connector-j-8.0.33.jar /opt/jboss/wildfly/modules/mysql/

ADD otrs.war /opt/jboss/wildfly/standalone/deployments/

COPY config.cli /opt/jboss/wildfly/bin/config.cli

RUN ["/opt/jboss/wildfly/bin/jboss-cli.sh", "--file=/opt/jboss/wildfly/bin/config.cli"]

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "--server-config=standalone-full.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
