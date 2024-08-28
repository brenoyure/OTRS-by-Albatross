##########################################################################################################################################
###                                                                                                                                    ###
###                                      V_03 - Criando o Usuário Administrador                                                        ###
###                                                                                                                                    ###
###  Com a nova regra de negócio, foi necessário implementar a autenticação e autorização dos usuários, com base em Roles.             ###
###  O SQL a seguir, criará as roles ADMIN e USER assim como o primeiro usuário do sistema, o 'Administrador'                          ###
###  para que seja possível logar sistema a primeira vez.                                                                              ###
###                                                                                                                                    ###
###  Este SQL garante a integridade dos dados, pois, a operação será feita dentro de uma transaction do MySQL.                         ###
###                                                                                                                                    ###
###  Na coluna de password, será inserido o hash senha do usuário no formato SHA-512                                                   ###
###                                                                                                                                    ###
###                                                                                                                                    ###
##########################################################################################################################################

START TRANSACTION;

INSERT INTO roles VALUES (1, 'ADMIN'), (2, 'USER');

INSERT INTO users VALUES(1, 'Administrador', '94W4ZJw0T9IVuH8Gf26kxbBbq2uMUFZLLxBBTYTppZaH7OCb12GFox4kjvHM1uc7qbgrHvyrx8S/mfXL2lkUhg==');

INSERT INTO users_roles VALUES (1, 1), (1, 2);

COMMIT;

