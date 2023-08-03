package br.albatross.otrs.domain.dao.otrs.chamados;

import static br.albatross.otrs.domain.models.otrs.ticket.Ticket_.queue;
import static br.albatross.otrs.domain.models.otrs.ticket.Ticket_.responsibleUser;
import static br.albatross.otrs.domain.models.otrs.ticket.Ticket_.service;
import static br.albatross.otrs.domain.models.otrs.ticket.Ticket_.ticketState;
import static br.albatross.otrs.domain.models.otrs.ticket.state.TicketState_.ticketStateType;

import java.util.List;

import br.albatross.otrs.domain.dao.apis.chamados.ChamadosDao;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.otrs.queue.Queue_;
import br.albatross.otrs.domain.models.otrs.service.Service_;
import br.albatross.otrs.domain.models.otrs.ticket.Ticket;
import br.albatross.otrs.domain.models.otrs.ticket.state.TicketStateType_;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.JoinType;

@Stateless
public class ChamadosDaoOtrsImpl implements ChamadosDao {

	@PersistenceContext(unitName = "otrsdb")
	private EntityManager entityManager;

	private static final byte QUEUE_NIVEL_1 = 5;

	private static final byte TICKET_STATE_AS_NEW  = 1;
	private static final byte TICKET_STATE_AS_OPEN = 2;

	public List<DadosDoChamado> findAllOpened() {
		var cb      =  entityManager.getCriteriaBuilder();
		var cq      =  cb.createQuery(DadosDoChamado.class);
		var ticket  =  cq.from(Ticket.class);

		ticket
			.fetch(service,          JoinType.INNER);

		ticket
			.fetch(responsibleUser,  JoinType.INNER);

		ticket
			.fetch(ticketState,      JoinType.INNER);

		var predicateQueueEqualsToNivel1 = cb.equal(ticket.get(queue).get(Queue_.id), QUEUE_NIVEL_1);

		var predicateTicketNew  = cb.equal(ticket.get(ticketState).get(ticketStateType).get(TicketStateType_.id), TICKET_STATE_AS_NEW);
		var predicateTicketOpen = cb.equal(ticket.get(ticketState).get(ticketStateType).get(TicketStateType_.id), TICKET_STATE_AS_OPEN);

		var predicateServicosGarantiaValidos = cb.between(ticket.get(service).get(Service_.id), 221, 225);

		var predicateTicketNewOrOpen = cb.or(predicateTicketNew, predicateTicketOpen);

		var finalAndPredicateTicketOpenAndServicoValidoAndQueueNivel1 = cb.and(predicateServicosGarantiaValidos, predicateQueueEqualsToNivel1, predicateTicketNewOrOpen);
		return entityManager.createQuery(cq.where(finalAndPredicateTicketOpenAndServicoValidoAndQueueNivel1)).getResultList();

	}

}
