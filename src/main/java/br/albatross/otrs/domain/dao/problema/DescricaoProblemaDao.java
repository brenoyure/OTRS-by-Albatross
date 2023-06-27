package br.albatross.otrs.domain.dao.problema;

import java.util.List;

import br.albatross.otrs.domain.services.beans.DescricaoProblema;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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

	public List<DescricaoProblema> findAll() {
		return entityManager.createQuery("SELECT dp FROM DescricaoProblema dp", DescricaoProblema.class).getResultList();
	}

}
