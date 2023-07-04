package br.albatross.otrs.domain.dao.problema;

import static br.albatross.otrs.domain.services.beans.DescricaoProblema_.problema;

import java.util.List;

import br.albatross.otrs.domain.services.beans.DescricaoProblema;
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
		entityManager.merge(descricaoProblema);
	}

	public void remove(DescricaoProblema descricaoProblema) {
		entityManager.remove(descricaoProblema);
	}	

	public List<DescricaoProblema> findAll() {
		var cb                 =  entityManager.getCriteriaBuilder();
		var cq                 =  cb.createQuery(DescricaoProblema.class);
		var descricaoProblema  =  cq.from(DescricaoProblema.class);

		descricaoProblema.fetch(problema, JoinType.INNER);

		return entityManager.createQuery(cq).getResultList();

	}

}
