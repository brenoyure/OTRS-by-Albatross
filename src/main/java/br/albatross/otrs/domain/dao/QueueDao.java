package br.albatross.otrs.domain.dao;

import java.util.List;

import br.albatross.otrs.domain.models.queue.Queue;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class QueueDao {

	@PersistenceContext(unitName = "otrsdb")
	private EntityManager entityManager;

	public List<Queue> findAll() {
		final String jpql = "SELECT q FROM Queue q";
		var query = entityManager.createQuery(jpql, Queue.class);
		return query.getResultList();
	}
	
}
