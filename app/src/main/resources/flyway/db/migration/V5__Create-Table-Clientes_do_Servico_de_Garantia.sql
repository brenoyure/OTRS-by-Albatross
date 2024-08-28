/*
* V05 - Criação da Tabela para representar os clientes, empresa ou órgão de governo,
* que utilizarão o Serviços de Garantia
* 
* @author breno.brito
*
*/

CREATE TABLE IF NOT EXISTS clientes (

    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) UNIQUE NOT NULL,
    descricao VARCHAR(255) UNIQUE NOT NULL,
    numeros_para_contato VARCHAR(255),
    emails_para_contato TEXT,

    logradouro VARCHAR(100),
    numero VARCHAR(55),
    bairro VARCHAR(55),
    cidade VARCHAR(55),
    estado VARCHAR(55),
    cep VARCHAR(13),

    horario_inicio_do_expediente TIME NOT NULL,
    horario_fim_do_expediente TIME NOT NULL,

    possui_horario_de_almoco BOOLEAN NOT NULL,

    inicio_do_horario_de_almoco TIME,
    fim_do_horario_de_almoco TIME
);

SELECT 
'V05 - Criação da Tabela para representar os clientes, empresa ou órgão de governo,
que utilizarão o Serviços de Garantia';

DESC clientes;
