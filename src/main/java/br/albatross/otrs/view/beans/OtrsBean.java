package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.models.ticket.Service;
import br.albatross.otrs.domain.models.ticket.Ticket;
import br.albatross.otrs.domain.services.TicketService;
import br.albatross.otrs.domain.services.beans.ConfigItemServiceBean;
import br.albatross.otrs.domain.services.beans.Problema;
import br.albatross.otrs.domain.services.garantia.EmailGarantia;
import br.albatross.otrs.domain.services.garantia.GarantiaService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
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

	@Getter @Setter
	private Ticket ticket;
	
	@Inject
	private TicketService ticketService;

	@Inject @Getter
	private List<Service> servicosValidos;

	@Getter @Setter
	private Problema problema = new Problema();

	@Inject
	private Validator<Ticket> validador;
	
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
		ticketService.buscarPeloNumeroDoTicket((String) value)

		.ifPresentOrElse(ticketFound -> {
				setTicket(ticketFound);
				validador.validate(context, componente, (Ticket)this.ticket);},

		 () -> {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ticket com o Número informado não encontrado.", null));});
	}

	public void enviarSolicitacaoDeGarantiaPorEmail() {
		garantiaService.enviarEmail(new EmailGarantia(problema, problema.getDescricao(),"breno.brito@mailtrap", "Problema Mouse - Garantia"));
		context.addMessage("otrs", new FacesMessage("E-mail enviado com Sucesso."));
	}

}
