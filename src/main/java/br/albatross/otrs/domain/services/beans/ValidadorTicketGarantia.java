package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;
import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;

import br.albatross.otrs.domain.dao.TicketDao;
import br.albatross.otrs.domain.models.ticket.Ticket;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;

@RequestScoped
public class ValidadorTicketGarantia implements Validator<String> {

	@Inject
	private TicketDao dao;
	
	private static String nomeDoServicoDoTicket;

	private static FacesMessage ticketNotFoundValidatorExceptionMessage = new FacesMessage(SEVERITY_WARN, "Ticket com o número informado não encontrado.", null);
	private static FacesMessage invalidTicketValidatorExceptionMessage  = new FacesMessage(SEVERITY_ERROR, "O Tipo do Serviço do Ticket é inválido.", "O Serviço do Ticket informado está como " + nomeDoServicoDoTicket + " que não é um serviço válido para Garantia.");
	private static FacesMessage validTicketFoundValidatorMessage        = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket Validado com Sucesso.", null);

	@Override
	public void validate(FacesContext context, UIComponent component, String ticketNumber) throws ValidatorException {
		var optionalTicket = dao.findTicketNumber(ticketNumber);

		if (optionalTicket.isEmpty())
			throw new ValidatorException(ticketNotFoundValidatorExceptionMessage);

		var ticket = optionalTicket.get();
		nomeDoServicoDoTicket = ticket.getService().getName();

		if (!ticketServiceValidoParaGarantia(ticket))
			throw new ValidatorException(invalidTicketValidatorExceptionMessage);

		context.addMessage("otrs", validTicketFoundValidatorMessage);

	}

	/**
	 * 
	 * @param ticket
	 * @return se o ticket é válido para o serviço de garantia ou não.
	 */
	private boolean ticketServiceValidoParaGarantia(Ticket ticket) {
		return ticket.getService().getId() > 220 && ticket.getService().getId() < 225;
	}
}