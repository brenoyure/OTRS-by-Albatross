package br.albatross.otrs.domain.dao;

import static br.albatross.otrs.domain.models.ticket.TicketState_.ticketStateType;
import static br.albatross.otrs.domain.models.ticket.Ticket_.queue;
import static br.albatross.otrs.domain.models.ticket.Ticket_.responsibleUser;
import static br.albatross.otrs.domain.models.ticket.Ticket_.service;
import static br.albatross.otrs.domain.models.ticket.Ticket_.ticketState;
import static java.util.Optional.empty;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.domain.models.queue.Queue_;
import br.albatross.otrs.domain.models.ticket.Service_;
import br.albatross.otrs.domain.models.ticket.Ticket;
import br.albatross.otrs.domain.models.ticket.TicketStateType_;
import br.albatross.otrs.domain.models.ticket.Ticket_;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;

@Stateless
public class TicketDao {

	@PersistenceContext(unitName = "otrsdb")
	private EntityManager entityManager;

	private static final Integer QUEUE_NIVEL_1 = 5;

	public Optional<Ticket> findById(Long id) {
		try {
			var cb      =  entityManager.getCriteriaBuilder();
			var cq      =  cb.createQuery(Ticket.class);
			var ticket  =   cq.from(Ticket.class);

			ticket.fetch(service, JoinType.LEFT);

			ticket
			    .fetch(ticketState,     JoinType.INNER)
			    .fetch(ticketStateType, JoinType.INNER);

			ticket.fetch(responsibleUser, JoinType.INNER);

			cq.where(cb.equal(ticket.get(Ticket_.id), id));

			return Optional.of(entityManager.createQuery(cq).getSingleResult());

		} catch (NoResultException e) {	return empty();	}

	}

	public Optional<Ticket> findByTicketNumber(String ticketNumber) {
		try {
			var cb = entityManager.getCriteriaBuilder();
			var cq = cb.createQuery(Ticket.class);
			var ticket = cq.from(Ticket.class);

			ticket.fetch(service, JoinType.LEFT);

			ticket
			    .fetch(ticketState,     JoinType.INNER)
			    .fetch(ticketStateType, JoinType.INNER);

			ticket.fetch(responsibleUser, JoinType.INNER);

			var predicateTicketEqualsToTicketNumber = cb.equal(ticket.get(Ticket_.ticketNumber), ticketNumber);

			return Optional.of(entityManager.createQuery(cq.where(predicateTicketEqualsToTicketNumber)).getSingleResult());

		} catch (NoResultException e) {	return empty();	}

	}

	public List<Ticket> findAllNewOrOpenedTicketsForNivel1Queue() {
		var cb      =  entityManager.getCriteriaBuilder();
		var cq      =  cb.createQuery(Ticket.class);
		var ticket  =  cq.from(Ticket.class);

		ticket
			.fetch(service,          JoinType.INNER);

		ticket
			.fetch(responsibleUser,  JoinType.INNER);

		ticket
			.fetch(ticketState,      JoinType.INNER)
			.fetch(ticketStateType,  JoinType.INNER);

		var predicateQueueEqualsToNivel1 = cb.equal(ticket.get(queue).get(Queue_.id), QUEUE_NIVEL_1);

		var predicateTicketNew  = cb.equal(ticket.get(ticketState).get(ticketStateType).get(TicketStateType_.id), 1);
		var predicateTicketOpen = cb.equal(ticket.get(ticketState).get(ticketStateType).get(TicketStateType_.id), 2);

		var predicateServicosGarantiaValidos = cb.between(ticket.get(service).get(Service_.id), 220, 225);

		var predicateTicketNewOrOpen = cb.or(predicateTicketNew, predicateTicketOpen);

		var finalAndPredicateTicketOpenAndServicoValidoAndQueueNivel1 = cb.and(predicateServicosGarantiaValidos, predicateQueueEqualsToNivel1, predicateTicketNewOrOpen);
		return entityManager.createQuery(cq.where(finalAndPredicateTicketOpenAndServicoValidoAndQueueNivel1)).getResultList();

	}

}
