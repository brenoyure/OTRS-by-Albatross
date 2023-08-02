package br.albatross.otrs.domain.dao.otrs.inventario;

import java.util.Optional;

import br.albatross.otrs.domain.dao.apis.equipamentos.InventarioDao;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Default;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Stateless @Default
public class InventarioDaoOtrsImpl implements InventarioDao {

	@PersistenceContext(unitName = "otrsdb")
	private EntityManager entityManager;

	/**
	 * Busca o número de série pelo BM do equipamento utilizando a API de Criteria da JPA.
	 * 
	 * @param bm
	 * @return optional contendo ou não o número de série.
	 */
	public Optional<String> buscarNumeroDeSeriePeloBm(String bm) {

		try {
			return Optional.of(
					(String) entityManager
								.createNativeQuery(getNativeQuery(bm), String.class)
								.setMaxResults(1)
								.getSingleResult());

		} catch (NoResultException e) {	return Optional.empty(); }

	}

	private String getNativeQuery(String bm) {
		return String.format("""

SELECT 
    x.xml_content_value
FROM
    configitem_version civ
        INNER JOIN
    xml_storage x ON civ.id = x.xml_key
WHERE
			(x.xml_content_key = '[1]{''Version''}[1]{''NumeroDeSerie''}[1]{''Content''}'
            OR
            x.xml_content_key = '[1]{''Version''}[1]{''SerialNumber_Hardware''}[1]{''Content''}')

		AND

			NOT x.xml_type = 'ITSM::ConfigItem::Archiv::22'

		AND
			civ.name = '%s';
					
					""", bm);
	}

}
