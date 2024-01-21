package br.albatross.otrs.domain.services.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.dao.problema.DescricaoProblemaDao;
import br.albatross.otrs.domain.dao.problema.ProblemaDao;
import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema;
import br.albatross.otrs.domain.models.garantia.entidades.problemas.Problema;
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
	private ProblemaDao problemasDao;
	
	@Inject
	private DescricaoProblemaDao descricaoProblemaDao;

	@Inject
	private FacesContext context;

	@Getter
	private List<Problema> problemas;

	@Getter
	private List<DescricaoProblema> descricaoProblemas;
	
	@PostConstruct
	void init() {
		problemas = problemasDao.findAll();
		descricaoProblemas = descricaoProblemaDao.findAll();
	}

	public void salvarProblema(Problema problema) {
		if (problema.getId() == null)
			problemasDao.persist(problema);
		else
			problemasDao.update(problema);

		context.addMessage("problemas", new FacesMessage(String.format("Problema '%s' salvo com sucesso", problema.getTipo())));
		atualizarListaProblemas();
	}

	public void salvarDescricaoProblema(DescricaoProblema descricaoProblema) {
		if (descricaoProblema.getId() == null)
			descricaoProblemaDao.persist(descricaoProblema);
		else
			descricaoProblemaDao.update(descricaoProblema);

		context.addMessage("problemas", new FacesMessage("Nova Descrição salva com sucesso"));
		atualizarListaDescricaoProblemas();
	}

	public void removerDescricaoProblema(DescricaoProblema descricaoProblema) {
		descricaoProblemaDao.remove(descricaoProblema);
		context.addMessage("problemas", new FacesMessage("Descrição removida com sucesso"));
		atualizarListaDescricaoProblemas();
	}

	private void atualizarListaProblemas() {
		problemas = problemasDao.findAll();
	}

	private void atualizarListaDescricaoProblemas() {
		descricaoProblemas = descricaoProblemaDao.findAll();
	}
	
}
