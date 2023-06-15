package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.models.ticket.Service;
import br.albatross.otrs.domain.models.ticket.Ticket;
import br.albatross.otrs.domain.services.EmailProducer;
import br.albatross.otrs.domain.services.TicketService;
import br.albatross.otrs.domain.services.beans.ConfigItemServiceBean;
import br.albatross.otrs.domain.services.garantia.EmailGarantia;
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

	@Getter @Setter
	private String bm;

	@Inject
	private TicketService ticketService;

	@Inject @Getter
	private List<Service> servicosValidos;

	@Getter @Setter
	private EmailGarantia emailGarantia = new EmailGarantia();

	@Inject
	private Validator<Ticket> validador;
	
	@Inject
	private EmailProducer emailProducer;

	@Inject
	private FacesContext context;

	public void buscarNumeroDeSeriePeloBm() {
		service
			.buscarNumeroDeSeriePorBm(bm)
			.ifPresent(NdeSerie -> emailGarantia.setNumeroDeSerie(NdeSerie));
	}

	public void enviarSolicitacaoDeGarantiaPorEmail() {
		emailProducer.enviarEmailParaAJmsQueue(this.emailGarantia);
		context.addMessage("otrs", new FacesMessage("E-mail enviado com Sucesso."));
	}

	public void validarTicket(FacesContext context, UIComponent componente, Object value) {
		ticketService.buscarPeloNumeroDoTicket((String) value)

		.ifPresentOrElse(ticketFound -> {
				setTicket(ticketFound);
				validador.validate(context, componente, (Ticket)this.ticket);},

		 () -> {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ticket Não encontrado.", "Ticket não localizado ou ainda não foi definido um serviço para o mesmo."));});
	}

}
