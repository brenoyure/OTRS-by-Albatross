package br.albatross.otrs.controllers;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import br.albatross.otrs.domain.services.UserService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserController {

	@Inject
	private UserService service;

	@GET
	@Produces(value = APPLICATION_JSON)
	public Response buscarTodos() {
		return Response.ok(service.listarTodos()).build();
	}

	@GET @Path("/id={id}")
	@Produces(value = APPLICATION_JSON)
	public Response buscaPorId(@PathParam("id") Integer id) {
		var userOptional = service.buscaPorId(id);
		return (userOptional.isPresent()) ? 
				Response.ok(userOptional.get()).build() : Response.noContent().build();
		
	}

}
