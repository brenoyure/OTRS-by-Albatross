package br.albatross.otrs.domain.services.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema;
import br.albatross.otrs.repositories.problema.DescricaoProblemaRepository;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;

@ViewScoped
public class TextosProntosService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DescricaoProblemaRepository dao;

	public List<DescricaoProblema> getListaDeProblemas() {
		return dao.findAll();
	}

}
