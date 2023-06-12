package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.models.ticket.Service;
import br.albatross.otrs.domain.services.beans.ConfigItemServiceBean;
import br.albatross.otrs.domain.services.beans.Problema;
import br.albatross.otrs.domain.services.beans.ValidadorTicketGarantia;
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
	
	@Inject @Getter
	private List<Service> servicosValidos;

	@Getter @Setter
	private Problema problema = new Problema();
	
	@Inject
	private ValidadorTicketGarantia validador;
	
	public void buscarNumeroDeSeriePeloBm() {
		service
			.buscarNumeroDeSeriePorBm(problema.getBm())
			.ifPresent(NdeSerie -> problema.setNumeroDeSerie(NdeSerie));
	}

	public void validarTicket(FacesContext context, UIComponent componente,Object value) {
		validador.validate(context, componente, (String)value);
	}

}
