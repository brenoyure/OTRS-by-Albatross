FROM quay.io/wildfly/wildfly:33.0.1.Final-jdk17

WORKDIR /opt/jboss/wildfly/

EXPOSE 8080

ARG MESSAGING_ACTIVEMQ_HOST=${MESSAGING_ACTIVEMQ_HOST}
ARG MESSAGING_ACTIVEMQ_USERNAME=${MESSAGING_ACTIVEMQ_USERNAME}
ARG MESSAGING_ACTIVEMQ_PASSWORD=${MESSAGING_ACTIVEMQ_PASSWORD}

ARG OTRS_MAIL_SESSION_USER=${OTRS_MAIL_SESSION_USER}
ARG OTRS_MAIL_SESSION_PASSWORD=${OTRS_MAIL_SESSION_PASSWORD}
ARG OTRS_MAIL_SMTP_HOST=${OTRS_MAIL_SMTP_HOST}
ARG OTRS_MAIL_SMTP_PORT=${OTRS_MAIL_SMTP_PORT}

COPY config.cli /tmp/

RUN ["/opt/jboss/wildfly/bin/jboss-cli.sh", "--file=/tmp/config.cli", "--resolve-parameter-values"]

COPY target/*.war /opt/jboss/wildfly/standalone/deployments/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-full.xml"]
