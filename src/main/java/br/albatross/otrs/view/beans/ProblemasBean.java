package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.dao.problema.DescricaoProblemaDao;
import br.albatross.otrs.domain.dao.problema.ProblemaDao;
import br.albatross.otrs.domain.services.beans.DescricaoProblema;
import br.albatross.otrs.domain.services.beans.Problema;
import jakarta.annotation.PostConstruct;
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

	@PostConstruct
	void init() {
		problemas = dao.findAll();
		descricaoProblemas = descricaoDao.findAll();
	}

	@Getter
	private List<Problema> problemas;
	
	@Getter
	private List<DescricaoProblema> descricaoProblemas;

	@Getter @Setter
	private Problema problema = new Problema();

	@Getter @Setter
	private DescricaoProblema descricaoProblema = new DescricaoProblema();

	public void salvarProblema() {
		dao.persist(problema);
	}

	public void salvarDescricaoProblema() {
		descricaoDao.persist(descricaoProblema);
	}

}
