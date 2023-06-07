package br.albatross.otrs.hello;

import java.io.IOException;

import br.albatross.otrs.domain.services.ConfigItemService;

import jakarta.inject.Inject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/configItem")
public class ConfigItemServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConfigItemService service;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		var numeroDeSerie = service.getNumeroDeSerieByBm("204704");
		var writer = resp.getWriter();

		numeroDeSerie.ifPresentOrElse(s -> writer.println(s), () -> writer.println("Not Found"));

	}

}
