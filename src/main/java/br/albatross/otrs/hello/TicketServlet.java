package br.albatross.otrs.hello;

import java.io.IOException;

import br.albatross.otrs.domain.services.TicketService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TicketService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var tickets = service.listarTodos();

		for(var ticket : tickets) {
			resp.getWriter().println("Ticket Nº: " + ticket.getTicketNumber() + " | Fila: " + ticket.getQueue().getName() + " | Cliente: " + ticket.getCustomerUserId());
			
		}
		

	}

}
