package br.albatross.otrs.controllers;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import br.albatross.otrs.domain.services.TicketService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/tickets")
public class TicketController {

	@Inject
	private TicketService service;

	@GET
	@Produces(value = APPLICATION_JSON)
	public Response buscarTodos() {
		return Response.ok(service.listarTodos()).build();
	}

}
