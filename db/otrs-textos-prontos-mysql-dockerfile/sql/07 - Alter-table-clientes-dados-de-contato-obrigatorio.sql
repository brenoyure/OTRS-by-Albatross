/*
* V07 - Alter table clientes
* 
* Agora será obrigatório informar, os dados de contato do cliente.
*
*/

LOCK TABLE clientes WRITE;

ALTER TABLE clientes
                MODIFY COLUMN numeros_para_contato VARCHAR(255) NOT NULL,
                MODIFY COLUMN emails_para_contato  TEXT         NOT NULL;

UNLOCK TABLE;

DESC clientes;

