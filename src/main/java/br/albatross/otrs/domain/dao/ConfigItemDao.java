package br.albatross.otrs.domain.dao;

import java.util.Optional;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class ConfigItemDao {

	@PersistenceContext
	private EntityManager entityManager;

	public Optional<String> findNumeroDeSerieByBmNative(String bm) {
		try {

			Query query = entityManager.createNativeQuery(getNativeQuery(bm), String.class);
			return Optional.of((String)query.getSingleResult());
		} 
		  catch (NoResultException e) { return Optional.empty(); } 

	}

	private String getNativeQuery(String bm) {
		return String.format("""
SELECT

    xmls.xml_content_value

FROM
    configitem ci
        LEFT JOIN
    configitem_version civ ON ci.id = civ.configitem_id
        LEFT JOIN
    xml_storage xmls ON civ.id = xmls.xml_key

WHERE
    civ.name = '%s'
        AND xmls.xml_content_key LIKE '%%Version%%'
        AND xmls.xml_content_key LIKE '%%NumeroDeSerie%%'
        AND xmls.xml_content_key LIKE '%%Content%%'
        
    ORDER BY
		ci.last_version_id DESC

	LIMIT 1;
""", bm);
	}

}
