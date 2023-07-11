package br.albatross.otrs.domain.dao.problema;

import static br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema_.descricaoDetalhada;
import static br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema_.descricaoResumida;
import static br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema_.id;
import static br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema_.problema;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema;
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
		var cb                     =  entityManager.getCriteriaBuilder();
		var cq                     =  cb.createCriteriaUpdate(DescricaoProblema.class);
		var rootDescricaoProblema  =  cq.from(DescricaoProblema.class);

		cq
		  .set(rootDescricaoProblema.get(problema),           descricaoProblema.getProblema())
		  .set(rootDescricaoProblema.get(descricaoResumida),  descricaoProblema.getDescricaoResumida())
		  .set(rootDescricaoProblema.get(descricaoDetalhada), descricaoProblema.getDescricaoDetalhada());

		entityManager
		        .createQuery(cq.where(cb.equal(rootDescricaoProblema.get(id), descricaoProblema.getId())))
		        .executeUpdate();
	}

	public void remove(DescricaoProblema descricaoProblema) {
		var cb = entityManager.getCriteriaBuilder();
		var cq = cb.createCriteriaDelete(DescricaoProblema.class);
		entityManager
				.createQuery(cq.where(cb.equal(cq.from(DescricaoProblema.class).get(id), descricaoProblema.getId())))
				.executeUpdate();
	}

	public List<DescricaoProblema> findAll() {
		var cb = entityManager.getCriteriaBuilder();
		var cq = cb.createQuery(DescricaoProblema.class);

		cq
		  .from(DescricaoProblema.class)
		  .fetch(problema, JoinType.INNER);

		return entityManager
				       .createQuery(cq)
				       .getResultList();

	}

}
