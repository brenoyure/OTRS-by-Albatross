package br.albatross.otrs.domain.services.beans.converters;

import java.util.Map;

import br.albatross.otrs.domain.models.garantia.entidades.problemas.Problema;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "problemaConverter")
public class ProblemaConverter implements Converter<Problema> {

	@Override
	public Problema getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null)
			return (Problema) getMapaObjetos(component).get(value);

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Problema problema) {
		if (problema == null) return null;

		adicionarAtributo(component, problema);
		return String.valueOf(problema.getId());
	}

	protected Map<String, Object> getMapaObjetos(UIComponent component) {
		return component.getAttributes();
	}

	protected void adicionarAtributo(UIComponent component, Problema problema) {
		String chave = String.valueOf(problema.getId());
		getMapaObjetos(component).put(chave, problema);
	}

}
