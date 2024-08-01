package br.albatross.otrs.domain.services.beans.converters;

import java.util.Map;

import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "fornecedorConverter")
public class FornecedorConverter implements Converter<DadosDoFornecedor> {

	@Override
	public DadosDoFornecedor getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null)
			return (DadosDoFornecedor) getMapaObjetos(component).get(value);

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, DadosDoFornecedor fornecedor) {
		if (fornecedor == null) return null;

		adicionarAtributo(component, fornecedor);
		return String.valueOf(fornecedor.getId());
	}

	protected Map<String, Object> getMapaObjetos(UIComponent component) {
		return component.getAttributes();
	}

	protected void adicionarAtributo(UIComponent component, DadosDoFornecedor fornecedor) {
		String chave = String.valueOf(fornecedor.getId());
		getMapaObjetos(component).put(chave, fornecedor);
	}

}
