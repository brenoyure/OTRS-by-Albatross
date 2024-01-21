package br.albatross.otrs.domain.services.beans.converters;

import java.util.Map;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "ticketConverter")
public class TicketConverter implements Converter<DadosDoChamado> {

	@Override
	public DadosDoChamado getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null)
			return (DadosDoChamado) getMapaObjetos(component).get(value);

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, DadosDoChamado dadosDoChamado) {
		if (dadosDoChamado == null) return null;

		adicionarAtributo(component, dadosDoChamado);
		return String.valueOf(dadosDoChamado.getId());
	}

	protected Map<String, Object> getMapaObjetos(UIComponent component) {
		return component.getAttributes();
	}

	protected void adicionarAtributo(UIComponent component, DadosDoChamado dadosDoChamado) {
		String chave = String.valueOf(dadosDoChamado.getId());
		getMapaObjetos(component).put(chave, dadosDoChamado);
	}

}
