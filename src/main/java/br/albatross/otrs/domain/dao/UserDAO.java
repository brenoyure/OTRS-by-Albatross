package br.albatross.otrs.domain.dao;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.domain.models.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public List<User> findAll() {
		var cb = entityManager.getCriteriaBuilder();
		var query = cb.createQuery(User.class);
		query.from(User.class);

		return entityManager.createQuery(query).getResultList();

	}

	public Optional<User> findById(Integer id) {
		var cb = entityManager.getCriteriaBuilder();
		var cq = cb.createQuery(User.class);
		var root = cq.from(User.class);

		cq.where(cb.equal(
					root.get("id"), id));

		try {
			return Optional.of(entityManager.createQuery(cq).getSingleResult());
		} catch (NoResultException e) { return Optional.empty(); }
	}

}
