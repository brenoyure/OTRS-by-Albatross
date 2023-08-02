package br.albatross.otrs.domain.services.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.dao.problema.DescricaoProblemaDao;
import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;

@ViewScoped
public class TextosProntosService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DescricaoProblemaDao dao;

	public TextosProntosService() {
		System.out.println("Criando textos prontos");
	}

	public List<DescricaoProblema> getListaDeProblemas() {
		return dao.findAll();
	}

}
