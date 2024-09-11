##########################################################################################################################################
###                                                                                                                                    ###
###                                      V_09 - Criando os Emails Templates (Emails Modelo                                             ###
###                                                                                                                                    ###
###  Com a nova regra de negócio, foi necessário implementar uma funcionalidade de e-mails templates, em que o usuário passa variáveis ###
###  no corpo do e-mail que serão substituído posteriormente (mas antes do envio de fato do e-amil) pelos dados de uma solicitação de  ###
###  garantia, por exemplo, nome de fornecedor, número de série de equipamento etc...                                                  ###
###                                                                                                                                    ###
###                                                                                                                                    ###
##########################################################################################################################################

CREATE TABLE IF NOT EXISTS email_template (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(255) UNIQUE NOT NULL,
    assunto VARCHAR(255) NOT NULL,
    corpo_do_email TEXT NOT NULL
);

INSERT INTO email_template VALUES (1, 'Email modelo básico para solicitações de garantia com formulário', '[Ticket#$chamado.numeroDoTicket] Problema $problema.tipo $fornecedor.nome - $cliente.nome', 'Prezados,
Falamos do(a) $cliente.nome,
Segue em anexo o formulário preenchido para abertura de solicitação de garantia para o equipamento: $numeroDeSerie.

Prezado atendente do fornecedor $fornecedor.nome: 

O formulário possui as informações necessárias sobre a solicitação, como, a descrição detalhada do problema,
horários disponíveis, como também, os dados de endereço.

As solicitações de garantia são realizadas através de um sistema interno de Solicitações de Garantia, com 
o objetivo de automatizar os envios de solicitações para diversos fornecedores.

Atenciosamente,
--
$cliente.nome - $cliente.descricao
$cliente.endereco.cidade/$cliente.endereco.estado
Serviço de Tecnologia da Informação ($cliente.nome)
$cliente.numerosParaContato
$cliente.emailsParaContato');

DESC email_template;
