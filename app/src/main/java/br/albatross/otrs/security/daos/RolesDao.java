package br.albatross.otrs.security.daos;

import java.util.List;

import org.hibernate.jpa.AvailableHints;

import br.albatross.otrs.security.models.Role;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class RolesDao {

	@PersistenceContext(unitName = "otrsdb_textos_prontos")
	private EntityManager entityManager;

	public List<Role> getRoles() {
		return entityManager
				.createQuery("SELECT r FROM Role r", Role.class)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getResultList();
	}

	public List<Role> getRoles(Iterable<Integer> ids) {
		return entityManager
				.createQuery("SELECT r FROM Role r WHERE r.id IN ( ?1 )", Role.class)
				.setParameter(1, ids)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getResultList();
	}

}
