/*
* SQL Nativo do MySQL/MariaDB para recuperar os tickets, 
* filtrados pelo estado de aberto ou novo e com os serviços selecionados
*/
SELECT 
    t.id,
    t.tn,
    t.title,
    t.service_id,
    s.name,
    t.customer_user_id
FROM
    ticket t
        INNER JOIN
    service s ON t.service_id = s.id
        INNER JOIN
    ticket_state ts ON t.ticket_state_id = ts.id

WHERE

        t.queue_id != 3
    AND
        (ts.type_id = 1 
        OR ts.type_id = 2)
    AND 
        t.service_id IN (99, 100, 101, 102, 129, 130, 131, 132, 133, 134);
