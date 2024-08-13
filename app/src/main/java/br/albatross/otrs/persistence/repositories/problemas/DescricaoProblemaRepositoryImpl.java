package br.albatross.otrs.persistence.repositories.problemas;

import static br.albatross.otrs.persistence.entities.problemas.DescricaoProblema_.problema;
import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;

import br.albatross.otrs.persistence.entities.problemas.DescricaoProblema;
import br.albatross.otrs.persistence.entities.problemas.Problema_;
import br.albatross.otrs.persistence.repositories.RepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;

@ApplicationScoped
public class DescricaoProblemaRepositoryImpl extends RepositoryImpl<DescricaoProblema, Integer> implements DescricaoProblemaRepository {

    @PersistenceContext(unitName = "otrsdb_textos_prontos")
	private EntityManager entityManager;

    public DescricaoProblemaRepositoryImpl() {
        super(DescricaoProblema.class);
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

}
