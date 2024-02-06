package br.albatross.otrs.domain.dao.relatorios;

import java.util.List;

import org.hibernate.jpa.AvailableHints;

import br.albatross.otrs.domain.dao.apis.relatorios.RelatorioServicoDeGarantiaDao;
import br.albatross.otrs.domain.models.garantia.apis.relatorios.RelatorioServicoDeGarantia;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class RelatorioServicoDeGarantiaDaoImpl implements RelatorioServicoDeGarantiaDao {

	private static final int FIRST_SERVICE_FOR_BETWEEN_PREDICATE = 221;
	private static final int LAST_SERVICE_FOR_BETWEEN_PREDICATE = 224;
	private static final byte SERVICE_NAME_FORMAT_SUBSTRING_INDEX = 17;

	@PersistenceContext(unitName = "otrsdb")
	private EntityManager entityManager;

	@Override
	public List<RelatorioServicoDeGarantia> getRelatorio() {

		return entityManager.createQuery(
				"SELECT new br.albatross.otrs.domain.models.garantia.entidades.relatorios.RelatorioServicoDeGarantiaDto(COUNT(t), t.service.id, SUBSTRING(t.service.name, ?1), MAX(t.createTime)) FROM Ticket t JOIN t.service WHERE t.service.id BETWEEN ?2 AND ?3 GROUP BY t.service.id, t.service.name", RelatorioServicoDeGarantia.class)
				.setParameter(1, SERVICE_NAME_FORMAT_SUBSTRING_INDEX)
				.setParameter(2, FIRST_SERVICE_FOR_BETWEEN_PREDICATE)
				.setParameter(3, LAST_SERVICE_FOR_BETWEEN_PREDICATE)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getResultList();

	}

	@Override
	public List<RelatorioServicoDeGarantia> getRelatorioMensal() {

		return entityManager.createQuery(
				"SELECT new br.albatross.otrs.domain.models.garantia.entidades.relatorios.RelatorioServicoDeGarantiaDto(COUNT(t), t.service.id, SUBSTRING(t.service.name, ?1), MAX(t.createTime)) FROM Ticket t JOIN t.service WHERE (t.service.id BETWEEN ?2 AND ?3) AND (MONTH(t.createTime) = MONTH(CURRENT_DATE) AND YEAR(t.createTime) = YEAR(CURRENT_DATE)) GROUP BY t.service.id, t.service.name", RelatorioServicoDeGarantia.class)
				.setParameter(1, SERVICE_NAME_FORMAT_SUBSTRING_INDEX)
				.setParameter(2, FIRST_SERVICE_FOR_BETWEEN_PREDICATE)
				.setParameter(3, LAST_SERVICE_FOR_BETWEEN_PREDICATE)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getResultList();
	}

	@Override
	public List<RelatorioServicoDeGarantia> getRelatorioAnual() {

		return entityManager.createQuery(
				"SELECT new br.albatross.otrs.domain.models.garantia.entidades.relatorios.RelatorioServicoDeGarantiaDto(COUNT(t), t.service.id, SUBSTRING(t.service.name, ?1), MAX(t.createTime)) FROM Ticket t JOIN t.service WHERE (t.service.id BETWEEN ?2 AND ?3) AND (YEAR(t.createTime) = YEAR(CURRENT_DATE)) GROUP BY t.service.id, t.service.name", RelatorioServicoDeGarantia.class)
				.setParameter(1, SERVICE_NAME_FORMAT_SUBSTRING_INDEX)
				.setParameter(2, FIRST_SERVICE_FOR_BETWEEN_PREDICATE)
				.setParameter(3, LAST_SERVICE_FOR_BETWEEN_PREDICATE)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getResultList();

	}

}
