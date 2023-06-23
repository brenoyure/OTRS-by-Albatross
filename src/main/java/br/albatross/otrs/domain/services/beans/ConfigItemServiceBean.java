package br.albatross.otrs.domain.services.beans;

import static java.util.Optional.empty;

import java.util.Optional;

import br.albatross.otrs.domain.services.ConfigItemService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;

@RequestScoped
public class ConfigItemServiceBean {

	@Inject
	private ConfigItemService service;
	
	@Inject
	private FacesContext context;

	public Optional<String> buscarNumeroDeSeriePorBm(String bm) {

		if (bm == null || bm.isBlank()) {
			context.addMessage("otrs", 
					new FacesMessage(FacesMessage.SEVERITY_WARN, "BM deve ser informado para realizar a consulta.", null));
			return empty();
		}

		var optional = service.getNumeroDeSerieByBm(bm);

		if (optional.isEmpty()) {
			context.addMessage("otrs", 
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Nº de Série não encontrado para o BM informado.", null));
		return empty();

		}

		return optional;

	}
	
}
