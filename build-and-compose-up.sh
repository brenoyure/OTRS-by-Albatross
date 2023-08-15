# Compila e enpacota o .war do projeto
mvn clean package;

# Copia o .war gerado para a pasta em que está localizado o dockerfile que será criado o app
cp target/otrs.war ./docker/otrs-wildfly-docker/

# Navega até a  pasta que contém o compose.yaml e roda o compose up no projeto
cd docker/ ;

# Faz o Build das Imagens dos Serviços (Containers)
docker compose build ;

# Cria os Serviços (Containers)
docker compose create otrsdb ; docker compose create otrsdb_textos_prontos ; docker compose create otrs-app ;

# Inicia os Serviços (Containers)
docker compose start
