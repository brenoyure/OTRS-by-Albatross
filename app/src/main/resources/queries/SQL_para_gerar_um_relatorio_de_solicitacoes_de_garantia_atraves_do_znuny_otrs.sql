
SELECT 
    COUNT(*)           AS 'total',
    s.id               AS 'id_servico',
    CASE
        WHEN LOCATE('::', s.name) > 0 THEN REVERSE(LEFT(REVERSE(s.name), (INSTR(REVERSE(s.name), '::') - 1)))
        WHEN LOCATE('::', s.name) = 0 THEN s.name
    END AS 'servico',
    MAX(t.create_time) AS 'data_e_hora_ultima_solicitacao'
FROM
    ticket t

INNER JOIN 
    service s ON s.id=t.service_id

GROUP BY 
    s.id, 'servico'

ORDER BY 
    'servico';
