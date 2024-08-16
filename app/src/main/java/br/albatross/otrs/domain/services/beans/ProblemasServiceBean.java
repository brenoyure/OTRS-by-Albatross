package br.albatross.otrs.domain.services.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.persistence.entities.problemas.DescricaoProblema;
import br.albatross.otrs.persistence.entities.problemas.Problema;
import br.albatross.otrs.persistence.repositories.problemas.DescricaoProblemaRepository;
import br.albatross.otrs.persistence.repositories.problemas.ProblemaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import lombok.Getter;

@ViewScoped
public class ProblemasServiceBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProblemaRepository problemaRepository;
	
	@Inject
	private DescricaoProblemaRepository descricaoProblemaRepository;

	@Inject
	private FacesContext context;

	@Getter
	private List<Problema> problemas;

	@Getter
	private List<DescricaoProblema> descricaoProblemas;

	@PostConstruct
	void init() {
		problemas = problemaRepository.findAll();
		descricaoProblemas = descricaoProblemaRepository.findAll();
	}

	public void salvarProblema(Problema problema) {
		if (problema.getId() == null)
			problemaRepository.persist(problema);
		else
			problemaRepository.merge(problema);

		context.addMessage("problemas", new FacesMessage(String.format("Problema '%s' salvo com sucesso", problema.getTipo())));
		atualizarListaProblemas();
	}

	public void salvarDescricaoProblema(DescricaoProblema descricaoProblema) {
		if (descricaoProblema.getId() == null)
			descricaoProblemaRepository.persist(descricaoProblema);
		else
			descricaoProblemaRepository.merge(descricaoProblema);

		context.addMessage("problemas", new FacesMessage("Nova Descrição salva com sucesso"));
		atualizarListaDescricaoProblemas();
	}

	public void removerDescricaoProblema(DescricaoProblema descricaoProblema) {

        descricaoProblemaRepository.deleteById(descricaoProblema.getId());
        descricaoProblemas.remove(descricaoProblema);
	    context.addMessage(null, new FacesMessage("Descrição removida com sucesso"));

	}

	private void atualizarListaProblemas() {
		problemas = problemaRepository.findAll();
	}

	private void atualizarListaDescricaoProblemas() {
		descricaoProblemas = descricaoProblemaRepository.findAll();
	}

}
