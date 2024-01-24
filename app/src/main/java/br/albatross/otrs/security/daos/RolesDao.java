package br.albatross.otrs.security.daos;

import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;

import br.albatross.otrs.security.models.Role;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class RolesDao {

	@PersistenceContext(unitName =  "otrsdb_textos_prontos")
	private EntityManager entityManager;

	public List<Role> getRoles() {
		return entityManager
				.createQuery("SELECT r FROM Role r", Role.class)
				.setHint(HINT_CACHEABLE, true)
				.getResultList();
	}

}
