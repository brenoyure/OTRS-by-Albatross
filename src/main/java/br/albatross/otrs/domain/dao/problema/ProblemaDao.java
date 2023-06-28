package br.albatross.otrs.domain.dao.problema;

import java.util.List;

import br.albatross.otrs.domain.services.beans.Problema;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProblemaDao {

	@PersistenceContext(unitName = "otrsdb_textos_prontos")
	private EntityManager entityManager;

	public void persist(Problema problema) {
		entityManager.persist(problema);
	}

	public void update(Problema problema) {
		entityManager.merge(problema);
	}

	public List<Problema> findAll() {
		var cq = entityManager.getCriteriaBuilder().createQuery(Problema.class);
		cq.from(Problema.class);
		return entityManager.createQuery(cq).getResultList();
	}

}
