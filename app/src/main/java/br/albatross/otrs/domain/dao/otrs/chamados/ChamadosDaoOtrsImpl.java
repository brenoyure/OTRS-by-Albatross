package br.albatross.otrs.domain.dao.otrs.chamados;

import static br.albatross.otrs.domain.models.otrs.service.Service_.name;
import static br.albatross.otrs.domain.models.otrs.ticket.Ticket_.customerUserId;
import static br.albatross.otrs.domain.models.otrs.ticket.Ticket_.id;
import static br.albatross.otrs.domain.models.otrs.ticket.Ticket_.queue;
import static br.albatross.otrs.domain.models.otrs.ticket.Ticket_.service;
import static br.albatross.otrs.domain.models.otrs.ticket.Ticket_.ticketNumber;
import static br.albatross.otrs.domain.models.otrs.ticket.Ticket_.ticketState;
import static br.albatross.otrs.domain.models.otrs.ticket.Ticket_.title;
import static br.albatross.otrs.domain.models.otrs.ticket.state.TicketState_.ticketStateType;

import java.util.List;

import br.albatross.otrs.domain.dao.apis.chamados.ChamadosDao;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamadoDto;
import br.albatross.otrs.domain.models.otrs.queue.Queue_;
import br.albatross.otrs.domain.models.otrs.service.Service_;
import br.albatross.otrs.domain.models.otrs.ticket.Ticket;
import br.albatross.otrs.domain.models.otrs.ticket.state.TicketStateType_;
import jakarta.persistence.EntityManager;

/**
 * Representa o contexto de persistência com o Sistema de Chamados OTRS/Znuny, 
 * para operações de leitura com a entidade de Chamados
 * 
 * @deprecated Devido a complexidade do SQL para recupera a lista de chamados, esta implementação está depreciada
 * 
 */
@Deprecated(forRemoval = true)
public class ChamadosDaoOtrsImpl implements ChamadosDao {

	private EntityManager entityManager;

	private static final byte TRASH_QUEUE_ID = 3;

	private static final byte TICKET_STATE_AS_NEW  = 1;
	private static final byte TICKET_STATE_AS_OPEN = 2;

	@Override
	@Deprecated(forRemoval = true)
	public List<DadosDoChamado> findByService(List<Integer> servicesIds) {

		var cb      =  entityManager.getCriteriaBuilder();
		var cq      =  cb.createQuery(DadosDoChamado.class);
		var ticket  =  cq.from(Ticket.class);

		cq.select(
				cb.construct(DadosDoChamadoDto.class, ticket.get(id), 
						                              ticket.get(ticketNumber), 
						                              ticket.get(title), 
						                              ticket.get(service).get(Service_.id), 
						                              ticket.get(service).get(name),
						                              ticket.get(customerUserId)));

		var predicateQueueNotEqualsToTrash = cb.notEqual(ticket.get(queue).get(Queue_.id), TRASH_QUEUE_ID);

		var predicateTicketNew  = cb.equal(ticket.get(ticketState).get(ticketStateType).get(TicketStateType_.id), TICKET_STATE_AS_NEW);
		var predicateTicketOpen = cb.equal(ticket.get(ticketState).get(ticketStateType).get(TicketStateType_.id), TICKET_STATE_AS_OPEN);

		var predicateServicosGarantiaValidos = ticket.get(service).get(Service_.id).in(servicesIds);

		var predicateTicketNewOrOpen = cb.or(predicateTicketNew, predicateTicketOpen);

		var predicateTicketNewOrOpenAndQueueNotEqualsToTrash = cb.and(predicateQueueNotEqualsToTrash, predicateTicketNewOrOpen);

		var finalAndPredicateTicketNewOrOpenAndQueueNotEqualsToTrashAndServicosValidos = cb.and(predicateTicketNewOrOpenAndQueueNotEqualsToTrash, predicateServicosGarantiaValidos);

		return entityManager.createQuery(cq.where(finalAndPredicateTicketNewOrOpenAndQueueNotEqualsToTrashAndServicosValidos)).getResultList();

	}

}
