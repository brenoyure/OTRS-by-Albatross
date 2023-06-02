package br.albatross.otrs.domain.services;

import java.util.List;

import br.albatross.otrs.domain.dao.TicketDao;
import br.albatross.otrs.domain.models.ticket.Ticket;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class TicketService {

	@Inject
	private TicketDao dao;

	public List<Ticket> listarTodos() {
		return dao.findAll();
	}

	public Ticket buscaPeloNumeroDoTicket(Long numeroTicket) {
		return dao.findByTicketNumber(numeroTicket);
	}

}
