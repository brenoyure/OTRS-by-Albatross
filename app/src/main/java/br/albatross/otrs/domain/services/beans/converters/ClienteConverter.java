package br.albatross.otrs.domain.services.beans.converters;

import java.util.Map;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "clienteConverter")
public class ClienteConverter implements Converter<DadosDoCliente> {

	@Override
	public DadosDoCliente getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null)
			return (DadosDoCliente) getMapaObjetos(component).get(value);

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, DadosDoCliente cliente) {
		if (cliente == null) return null;

		adicionarAtributo(component, cliente);
		return String.valueOf(cliente.getId());
	}

	protected Map<String, Object> getMapaObjetos(UIComponent component) {
		return component.getAttributes();
	}

	protected void adicionarAtributo(UIComponent component, DadosDoCliente cliente) {
		String chave = String.valueOf(cliente.getId());
		getMapaObjetos(component).put(chave, cliente);
	}

}
