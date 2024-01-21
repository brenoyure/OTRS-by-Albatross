SELECT

    civ.name AS 				'BM' ,
    xml.xml_content_value AS 	'Nº de Série',
    ci.change_time AS			'Última Alteração'
    
FROM
    configitem ci
        LEFT JOIN
    configitem_version civ ON ci.id = civ.configitem_id
        LEFT JOIN
    xml_storage xml ON civ.id = xml.xml_key
WHERE
    civ.name = '204704'
        AND xml.xml_content_key = '[1]{\'Version\'}[1]{\'NumeroDeSerie\'}[1]{\'Content\'}'
        
    ORDER BY
		ci.last_version_id DESC;