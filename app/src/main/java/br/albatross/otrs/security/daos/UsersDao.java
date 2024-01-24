package br.albatross.otrs.security.daos;

import static org.hibernate.jpa.HibernateHints.HINT_CACHEABLE;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.security.models.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class UsersDao {

	@PersistenceContext(unitName = "otrsdb_textos_prontos")
	private EntityManager entityManager;

	public void persist(User user) {
		entityManager.persist(user);
	}

	public boolean existsByUsername(String username) {
		try {
			return entityManager
					.createQuery("SELECT EXISTS(SELECT u FROM User u WHERE u.username = ?1)", Boolean.class)
					.setParameter(1, username)
					.getSingleResult();
		} catch (NoResultException e) {	return false; }
	}

	public List<User> findAll() {
		return entityManager
				.createQuery("SELECT u FROM User u", User.class)
				.setHint(HINT_CACHEABLE, true)
				.getResultList();
	}

	public Optional<User> findById(int id) {
		return Optional.ofNullable(entityManager.find(User.class, id));
	}

	public void atualizar(User user) {
		var cb   = entityManager.getCriteriaBuilder();
		var cq   = cb.createCriteriaUpdate(User.class);
		var root = cq.from(User.class);

		cq
			.set("username", user.getUsername())
			.set("password", user.getPassword())
			.set("roles",    user.getRoles())
			.where(cb.equal(root.get("id"), user.getId()));

		entityManager
			.createQuery(cq)
			.executeUpdate();

	}

}
































