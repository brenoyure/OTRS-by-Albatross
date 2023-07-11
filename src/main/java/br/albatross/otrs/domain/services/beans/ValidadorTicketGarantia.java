package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.Serializable;

import br.albatross.otrs.domain.models.otrs.ticket.Ticket;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;

@ViewScoped
public class ValidadorTicketGarantia implements Validator<Ticket>, Serializable {

	private static final long serialVersionUID = 1L;

	private static FacesMessage invalidTicketServiceValidatorExceptionMessage;
//	private static FacesMessage validTicketFoundValidatorMessage;

	@Override
	public void validate(FacesContext context, UIComponent component, Ticket ticket) throws ValidatorException {

		if (ticket == null) 
			throw new ValidatorException(new FacesMessage(SEVERITY_ERROR, "Ticket Nulo.", null));

		if (ticket.getService() == null) {
			throw new ValidatorException(new FacesMessage(SEVERITY_ERROR, "O Serviço do Ticket ainda foi não definido.", "Este problema pode ocorrer no caso de um novo chamado aberto, geralmente via e-mail. Atribua um Serviço para este Ticket e tente novamente."));
		}

		if (!ticketServiceValidoParaGarantia(ticket)) {
			invalidTicketServiceValidatorExceptionMessage = new FacesMessage(SEVERITY_ERROR, "O Serviço do Ticket é inválido.", "O Serviço do Ticket informado está como " + ticket.getService().getName() + " que não é um serviço válido para Garantia.");
			throw new ValidatorException(invalidTicketServiceValidatorExceptionMessage);
		}

		if (!ticketEstaAberto(ticket)) {
			throw new ValidatorException(new FacesMessage(SEVERITY_ERROR, "Ticket já fechado.", "O Ticket informado já está fechado."));
		}
//
//		validTicketFoundValidatorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket Validado com Sucesso.", ticket.getService().getName());
//		context.addMessage("otrs", validTicketFoundValidatorMessage);

	}

	/**
	 * 
	 * @param ticket
	 * @return se o ticket é válido para o serviço de garantia ou não.
	 */
	private boolean ticketServiceValidoParaGarantia(Ticket ticket) {
		return ticket.getService().getId() > 220 && ticket.getService().getId() < 225;
	}

	/**
	 * 
	 * @param ticket
	 * @return se o ticket é válido para o serviço de garantia ou não.
	 */
	private boolean ticketEstaAberto(Ticket ticket) {
		return (ticket.getTicketState().getTicketStateType().getId().equals(1) || ticket.getTicketState().getTicketStateType().getId().equals(2));
	}
	
}