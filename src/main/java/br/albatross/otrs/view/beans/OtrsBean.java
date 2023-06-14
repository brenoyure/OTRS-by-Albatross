package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.models.ticket.Service;
import br.albatross.otrs.domain.services.TicketService;
import br.albatross.otrs.domain.services.beans.ConfigItemServiceBean;
import br.albatross.otrs.domain.services.beans.Problema;
import br.albatross.otrs.domain.services.beans.ValidadorTicketGarantia;
import br.albatross.otrs.domain.services.garantia.EmailGarantia;
import br.albatross.otrs.domain.services.garantia.GarantiaService;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class OtrsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ConfigItemServiceBean service;

	@Inject
	private TicketService ticketService;

	@Inject @Getter
	private List<Service> servicosValidos;

	@Getter @Setter
	private Problema problema = new Problema();

	@Inject
	private ValidadorTicketGarantia validador;
	
	@Inject
	private GarantiaService garantiaService;

	@Inject
	private FacesContext context;

	public void buscarNumeroDeSeriePeloBm() {
		service
			.buscarNumeroDeSeriePorBm(problema.getBm())
			.ifPresent(NdeSerie -> problema.setNumeroDeSerie(NdeSerie));
	}

	public void validarTicket(FacesContext context, UIComponent componente, Object value) {
		validador.validate(context, componente, (String)value);
	}

	public void enviarSolicitacaoDeGarantiaPorEmail() {
		garantiaService.enviarEmail(new EmailGarantia(problema, problema.getDescricao(),"breno.brito@mailtrap", "Problema Mouse - Garantia"));
		context.addMessage("otrs", new FacesMessage("E-mail enviado com Sucesso."));
	}

}
