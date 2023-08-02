package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;

@ViewScoped
public class ValidadorTicketGarantia implements Validator<DadosDoChamado>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void validate(FacesContext context, UIComponent component, DadosDoChamado dadosDoChamado) throws ValidatorException {

		if (dadosDoChamado == null) 
			throw new ValidatorException(new FacesMessage(SEVERITY_ERROR, "Ticket Nulo.", null));

		if (dadosDoChamado.getDadosDoServico() == null) {
			throw new ValidatorException(new FacesMessage(SEVERITY_ERROR, "O Serviço do Chamado ainda foi não definido.", "Este problema pode ocorrer no caso de um novo chamado aberto, geralmente via e-mail. Atribua um Serviço para este Chamado e tente novamente."));
		}

	}
	
}