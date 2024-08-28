/*
* V06 - Alter table clientes
* 
* Agora será obrigatório informar, por completo, os dados de endereço.
*
*/

LOCK TABLE clientes WRITE;

ALTER TABLE clientes 
                MODIFY COLUMN logradouro VARCHAR(100) NOT NULL, 
                MODIFY COLUMN numero     VARCHAR(55)  NOT NULL,
                MODIFY COLUMN bairro     VARCHAR(55)  NOT NULL,
                MODIFY COLUMN cidade     VARCHAR(55)  NOT NULL,
                MODIFY COLUMN estado     VARCHAR(55)  NOT NULL,
                MODIFY COLUMN cep        VARCHAR(13)  NOT NULL;

UNLOCK TABLE;

DESC clientes;
