package br.albatross.otrs.domain.dao;

import java.util.List;

import br.albatross.otrs.domain.models.ticket.Ticket;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
			.fetch("queue");

		return entityManager.createQuery(query).getResultList();
	}
	
	public Ticket findByTicketNumber(Long ticketNumber) {
		var cb = entityManager.getCriteriaBuilder();
		var cq = cb.createQuery(Ticket.class);
		var ticket = cq.from(Ticket.class);
		
		cq.where(cb.equal(ticket.get("ticketNumber"), ticketNumber));
		
		return entityManager.createQuery(cq).getSingleResult();
		
	}

}
