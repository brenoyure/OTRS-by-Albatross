package br.albatross.otrs.domain.services.beans.converters;

import java.util.Map;

import br.albatross.otrs.domain.models.otrs.ticket.Ticket;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "ticketConverter")
public class TicketConverter implements Converter<Ticket> {

	@Override
	public Ticket getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null)
			return (Ticket) getMapaObjetos(component).get(value);

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Ticket ticket) {
		if (ticket == null) return null;

		adicionarAtributo(component, ticket);
		return String.valueOf(ticket.getId());
	}

	protected Map<String, Object> getMapaObjetos(UIComponent component) {
		return component.getAttributes();
	}

	protected void adicionarAtributo(UIComponent component, Ticket ticket) {
		String chave = String.valueOf(ticket.getId());
		getMapaObjetos(component).put(chave, ticket);
	}

}
