package br.albatross.otrs.domain.services;

import java.util.List;

import br.albatross.otrs.domain.dao.ServiceDao;
import br.albatross.otrs.domain.models.ticket.Service;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;

public class TicketServiceService {

	@Inject
	private ServiceDao dao;

	private static List<Integer> garantiaValidIds = List.of(221, 222, 223, 224);

	@Produces @ViewScoped
	public List<Service> listarServicosGarantiaValidos() {
		return dao.findAllValidServices(garantiaValidIds);
	}

}
