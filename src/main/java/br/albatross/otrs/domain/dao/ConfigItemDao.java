package br.albatross.otrs.domain.dao;

import java.util.Optional;

import br.albatross.otrs.domain.models.configitem.XmlStorage;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;

@Stateless
public class ConfigItemDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * No do atributo do XML que referencia a tabela/entidade ConfigItemVersion.
	 */
	private static final String CONFIG_ITEM_VERSION = "configItemVersion";
	
	/**
	 * No do atributo do XML que referencia a tabela/entidade ConfigItemVersion.
	 */
	private static final String ID = "id";

	/**
	 * Nome da Versão do Item de configuração, nesse caso o BM.
	 */
	private static final String CONFIG_ITEM_VERSION_NAME = "name";

	/**
	 * Atributo que representa um campo do XML.
	 */
	private static final String XML_CONTENT_KEY = "xmlContentKey";

	/**
	 * Campo que será filtrado no XML, no caso o Número de Série.
	 */
	private static final String XML_CONTENT_KEY_NUMERO_DE_SERIE = "[1]{\'Version\'}[1]{\'NumeroDeSerie\'}[1]{\'Content\'}";

	/**
	 * Busca o número de série pelo BM do computador utilizando a API de Criteria da JPA.
	 * 
	 * @param bm
	 * @return optional contendo ou não o número de série.
	 */
	public Optional<String> findNumeroDeSerieByBmCriteria(String bm) {

		try {
			var cb          =  entityManager.getCriteriaBuilder();
			var query       =  cb.createQuery(XmlStorage.class);
			var xmlStorage  =  query.from(XmlStorage.class);
			
			xmlStorage.fetch(CONFIG_ITEM_VERSION, JoinType.INNER);

			var predicateXmlKeyEqualsToConfigItemPkey = cb.equal(xmlStorage.get(CONFIG_ITEM_VERSION).get(CONFIG_ITEM_VERSION_NAME), bm);
			var predicateColumnEqualsToNumeroDeSerie = cb.equal(xmlStorage.get(XML_CONTENT_KEY), XML_CONTENT_KEY_NUMERO_DE_SERIE);

			var finalAndPredicate = cb.and(predicateXmlKeyEqualsToConfigItemPkey, predicateColumnEqualsToNumeroDeSerie);

			query.where(finalAndPredicate);

			query.orderBy(
						cb.desc(xmlStorage.get(CONFIG_ITEM_VERSION).get(ID)));


			return Optional.of(entityManager.createQuery(query).setMaxResults(1).getSingleResult().getXmlContentValue());
			
		} catch (NoResultException e) {	return Optional.empty(); }

	}

/*

	private Optional<String> findNumeroDeSerieByBmNative(String bm) {
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
 */
}
