package br.albatross.otrs.domain.services;

import java.util.List;
import java.util.Optional;

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

	public Ticket buscarPeloIdDoTicket(Long id) {
		return dao.findById(id);
	}

	public Optional<Ticket> buscarPeloNumeroDoTicket(String numeroDoTicket) {
		return dao.findByTicketNumber(numeroDoTicket);
	}

}
