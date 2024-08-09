package br.albatross.otrs.domain.services.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.persistence.entities.DescricaoProblema;
import br.albatross.otrs.persistence.repositories.problemas.DescricaoProblemaRepository;
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
