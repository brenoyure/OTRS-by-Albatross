package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.dao.problema.DescricaoProblemaDao;
import br.albatross.otrs.domain.dao.problema.ProblemaDao;
import br.albatross.otrs.domain.services.beans.DescricaoProblema;
import br.albatross.otrs.domain.services.beans.Problema;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class ProblemasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProblemaDao dao;
	
	@Inject
	private DescricaoProblemaDao descricaoDao;

	@Getter
	private List<Problema> problemas;

	@Getter
	private List<DescricaoProblema> descricaoProblemas;

	@Getter @Setter
	private Problema problema = new Problema();

	@Getter @Setter
	private DescricaoProblema descricaoProblema = new DescricaoProblema();

	@Inject
	private FacesContext context;

	@PostConstruct
	void init() {
		problemas = dao.findAll();
		descricaoProblemas = descricaoDao.findAll();
	}

	public void salvarProblema() {
		dao.persist(problema);
		problemas = dao.findAll();
		context.addMessage("problemas", new FacesMessage(String.format("Problema '%s' salvo com sucesso", problema.getTipo())));
		problema = new Problema();
	}

	public void salvarDescricaoProblema() {
		descricaoDao.persist(descricaoProblema);
		context.addMessage("problemas", new FacesMessage("Nova Descrição salva com sucesso"));
		descricaoProblemas = descricaoDao.findAll();
		this.descricaoProblema = new DescricaoProblema();
	}

}
