package br.albatross.otrs.domain.dao;

import static br.albatross.otrs.domain.models.configitem.ConfigItemVersion_.id;
import static br.albatross.otrs.domain.models.configitem.ConfigItemVersion_.name;
import static br.albatross.otrs.domain.models.configitem.XmlStorage_.configItemVersion;
import static br.albatross.otrs.domain.models.configitem.XmlStorage_.xmlContentKey;

import java.util.Optional;

import br.albatross.otrs.domain.models.configitem.XmlStorage;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;

@Stateless
public class ConfigItemDao {

	@PersistenceContext(unitName = "otrsdb")
	private EntityManager entityManager;

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

			xmlStorage.fetch(configItemVersion, JoinType.INNER);

			var predicateXmlKeyEqualsToConfigItemPkey = cb.equal(xmlStorage.get(configItemVersion).get(name), bm);
			var predicateColumnEqualsToNumeroDeSerie = cb.equal(xmlStorage.get(xmlContentKey), XML_CONTENT_KEY_NUMERO_DE_SERIE);

			var finalAndPredicate = cb.and(predicateXmlKeyEqualsToConfigItemPkey, predicateColumnEqualsToNumeroDeSerie);

			query.where(finalAndPredicate);

			query.orderBy(
						cb.desc(xmlStorage.get(configItemVersion).get(id)));


			return Optional.of(entityManager.createQuery(query).setMaxResults(1).getSingleResult().getXmlContentValue());

		} catch (NoResultException e) {	return Optional.empty(); }

	}

}
