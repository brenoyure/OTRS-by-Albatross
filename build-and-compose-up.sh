# Compila e enpacota o .war do projeto
mvn clean package;

# Copia o .war gerado para a pasta em que está localizado o dockerfile que será criado o app
cp target/otrs.war ./docker/otrs-wildfly-docker/

# Navega até a  pasta que contém o compose.yaml e roda o compose up no projeto
cd docker/ ;
docker-compose up
