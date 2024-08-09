package br.albatross.otrs.security.persistence.repositories;

import java.util.List;

import br.albatross.otrs.security.persistence.entities.Role;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class RolesDao {

	@PersistenceContext(unitName = "otrsdb_textos_prontos")
	private EntityManager entityManager;

	public List<Role> getRoles() {
		return entityManager
				.createQuery("SELECT r FROM Role r", Role.class)
				.getResultList();
	}

	public List<Role> getRoles(Iterable<Integer> ids) {
		return entityManager
				.createQuery("SELECT r FROM Role r WHERE r.id IN ( ?1 )", Role.class)
				.setParameter(1, ids)
				.getResultList();
	}

}
