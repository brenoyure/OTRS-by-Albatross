package br.albatross.otrs.domain.dao;

import static br.albatross.otrs.domain.models.ticket.Ticket_.queue;
import static java.util.Optional.empty;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.domain.models.ticket.Ticket;
import br.albatross.otrs.domain.models.ticket.Ticket_;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;

@Stateless
public class TicketDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Ticket> findAll() {
		var query = entityManager
							.getCriteriaBuilder()
							.createQuery(Ticket.class);
		query
			.from(Ticket.class)
			.fetch(queue);

		return entityManager.createQuery(query).getResultList();
	}
	
	public Ticket findById(Long ticketNumber) {
		var cb = entityManager.getCriteriaBuilder();
		var cq = cb.createQuery(Ticket.class);
		var ticket = cq.from(Ticket.class);

		cq.where(cb.equal(ticket.get(Ticket_.ticketNumber), ticketNumber));
		
		return entityManager.createQuery(cq).getSingleResult();
		
	}

	public Optional<Ticket> findByTicketNumber(String ticketNumber) {
		try {
			var cb = entityManager.getCriteriaBuilder();
			var cq = cb.createQuery(Ticket.class);
			var ticket = cq.from(Ticket.class);

			ticket.fetch(Ticket_.service, JoinType.INNER);
			ticket.fetch(Ticket_.responsibleUser, JoinType.INNER);

			var predicateTicketEqualsToTicketNumber = cb.equal(ticket.get(Ticket_.ticketNumber), ticketNumber);
			
			return Optional.of(entityManager.createQuery(cq.where(predicateTicketEqualsToTicketNumber)).getSingleResult());

		} catch (NoResultException e) {	return empty();	}
		
	}

}
