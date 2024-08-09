package br.albatross.otrs.persistence.repositories.problemas;

import static br.albatross.otrs.persistence.entities.Problema_.id;
import static br.albatross.otrs.persistence.entities.Problema_.tipo;

import java.util.List;

import br.albatross.otrs.persistence.entities.Problema;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class ProblemaRepositoryImpl implements ProblemaRepository {

	@PersistenceContext(unitName = "otrsdb_textos_prontos")
	private EntityManager entityManager;

	@Override
	public void persist(Problema problema) {
		entityManager.persist(problema);
	}

	@Override
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

	@Override
    public List<Problema> findAll() {
		var cq = entityManager.getCriteriaBuilder().createQuery(Problema.class);
		cq.from(Problema.class);
		return entityManager.createQuery(cq).getResultList();
	}

}
