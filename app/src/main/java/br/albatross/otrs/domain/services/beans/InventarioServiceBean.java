package br.albatross.otrs.domain.services.beans;

import static jakarta.faces.application.FacesMessage.SEVERITY_WARN;
import static java.util.Optional.empty;

import java.io.Serializable;
import java.util.Optional;

import br.albatross.otrs.externos.InventarioRepository;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;

@ViewScoped
public class InventarioServiceBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private InventarioRepository repository;

    @Inject
    private FacesContext context;

    public Optional<String> buscarNumeroDeSeriePorBm(String bm) {

		if (bm == null || bm.isBlank()) {
			context.addMessage("otrs", 
					new FacesMessage(SEVERITY_WARN, "BM deve ser informado para realizar a consulta.", null));
			return empty();
		}

		var optional = repository.findSerialNumberByIdentifier(bm);

		if (optional.isEmpty()) {
			context.addMessage("otrs", 
						new FacesMessage(SEVERITY_WARN, "Nº de Série não encontrado para o BM informado.", null));
		return empty();

		}

        return optional;

    }

}
