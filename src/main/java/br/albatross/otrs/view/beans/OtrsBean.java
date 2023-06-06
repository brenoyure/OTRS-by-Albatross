package br.albatross.otrs.view.beans;

import static jakarta.faces.context.FacesContext.getCurrentInstance;

import br.albatross.otrs.domain.services.ConfigItemService;

import jakarta.enterprise.context.RequestScoped;

import jakarta.faces.application.FacesMessage;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import lombok.Getter;
import lombok.Setter;

@Named @RequestScoped
public class OtrsBean {

	@Inject
	private ConfigItemService service;

	@Getter @Setter
	private String numeroDeSerie;

	@Getter @Setter
	private String bm;	

	public void buscarNumeroDeSeriePeloBm() {
		
		if (bm == null || bm.isBlank()) {
			getCurrentInstance()
			.addMessage("otrs", 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "BM deve ser informado para realizar a consulta.", null));
			return;
		}
		
		var optional = service.getNumeroDeSerieByBm(bm);

		if (optional.isEmpty()) {
			getCurrentInstance()
				.addMessage("otrs", 
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Nº de Série não encontrado para o BM informado.", null));
			numeroDeSerie = null;
			return;
		}

		if (optional.isPresent()) {
		    numeroDeSerie = optional.get();
		    return;
		}

	}
	
}
