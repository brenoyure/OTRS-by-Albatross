package br.albatross.otrs.domain.dao.problema;

import static br.albatross.otrs.domain.models.garantia.entidades.problemas.Problema_.id;
import static br.albatross.otrs.domain.models.garantia.entidades.problemas.Problema_.tipo;
import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.entidades.problemas.Problema;
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
		var           cb  =  entityManager.getCriteriaBuilder();
		var           cq  =  cb.createCriteriaUpdate(Problema.class);
		var rootProblema  =  cq.from(Problema.class);

		cq
		  .set(rootProblema.get(tipo), problema.getTipo());

		entityManager
		        .createQuery(cq.where(cb.equal(rootProblema.get(id), problema.getId())))
		        .executeUpdate();

	}

	public List<Problema> findAll() {
		var cq = entityManager.getCriteriaBuilder().createQuery(Problema.class);
		cq.from(Problema.class);
		return entityManager.createQuery(cq).setHint(HINT_CACHEABLE, true).getResultList();
	}

}
