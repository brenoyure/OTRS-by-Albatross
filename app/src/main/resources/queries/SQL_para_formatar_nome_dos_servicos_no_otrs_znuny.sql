/*
* SQL Nativo utilizando as funções do MySQL/MariaDB para formatar a saída do nome do
* do Serviço do Ticket do OTRS
*/

# instr(name, '::')

# 1.Muito baixo::Garantia Positivo::Monitor Positivo
# ovitisoP rotinoM::ovitisoP aitnaraG::oxiab otiuM.1  < -- Reversed

SELECT REVERSE(s.name) FROM service s WHERE id = 129;

SELECT INSTR(REVERSE(s.name), '::') FROM service s WHERE id = 129;

SELECT REVERSE(LEFT(REVERSE(s.name), (INSTR(REVERSE(s.name), '::') - 1))) FROM service s WHERE id = 102;

