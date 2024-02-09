package br.albatross.otrs.domain.dao.problema;

import static br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema_.problema;
import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema;
import br.albatross.otrs.domain.models.garantia.entidades.problemas.Problema_;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;

@Stateless
public class DescricaoProblemaDao {

	@PersistenceContext(unitName = "otrsdb_textos_prontos")
	private EntityManager entityManager;

	public void persist(DescricaoProblema descricaoProblema) {
		entityManager.persist(descricaoProblema);
	}

	public void update(DescricaoProblema descricaoProblema) {
		descricaoProblema = entityManager.merge(descricaoProblema);
	}

	public void remove(DescricaoProblema descricaoProblema) {
		entityManager.remove(entityManager.getReference(DescricaoProblema.class, descricaoProblema.getId()));
	}

	public List<DescricaoProblema> findAll() {
		var cb                =  entityManager.getCriteriaBuilder();
		var cq                =  cb.createQuery(DescricaoProblema.class);
		var descricaoProblema =  cq.from(DescricaoProblema.class); 

		descricaoProblema
		                 .fetch(problema, JoinType.INNER);

		cq
		  .orderBy(cb.asc(descricaoProblema.get(problema).get(Problema_.id)));

		return entityManager
				       .createQuery(cq)
				       .setHint(HINT_CACHEABLE, true)
				       .getResultList();

	}

}
