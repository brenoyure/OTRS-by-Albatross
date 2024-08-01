package br.albatross.otrs.domain.dao.problema;

import static br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema_.descricaoDetalhada;
import static br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema_.descricaoResumida;
import static br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema_.id;
import static br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema_.problema;
import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.domain.dao.apis.problemas.DescricaoProblemaDao;
import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema;
import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema_;
import br.albatross.otrs.domain.models.garantia.entidades.problemas.Problema_;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;

@Stateless
public class DescricaoProblemaDaoImpl implements DescricaoProblemaDao {

	@PersistenceContext(unitName = "otrsdb_textos_prontos")
	private EntityManager entityManager;

	@Override
    public void persist(DescricaoProblema descricaoProblema) {
		entityManager.persist(descricaoProblema);
	}

	@Override
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

	@Override
    public void remove(DescricaoProblema descricaoProblema) {
		var cb = entityManager.getCriteriaBuilder();
		var cq = cb.createCriteriaDelete(DescricaoProblema.class);
		entityManager
				.createQuery(cq.where(cb.equal(cq.from(DescricaoProblema.class).get(id), descricaoProblema.getId())))
				.executeUpdate();
	}

	@Override
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

	@Override
    public Optional<DescricaoProblema> findById(int id) {

	    try {

	        var cb = entityManager.getCriteriaBuilder();
	        var cq = cb.createQuery(DescricaoProblema.class);
	        var descricaoProblema = cq.from(DescricaoProblema.class);

			descricaoProblema.fetch(DescricaoProblema_.problema, JoinType.INNER);

	        return Optional.of(entityManager.createQuery(cq.where(cb.equal(descricaoProblema.get(DescricaoProblema_.id), id))).getSingleResult());

	    } catch (NoResultException e) { return Optional.empty(); }

	}

}
