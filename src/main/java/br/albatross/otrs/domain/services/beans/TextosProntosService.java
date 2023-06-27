package br.albatross.otrs.domain.services.beans;

import java.util.List;

import br.albatross.otrs.domain.dao.problema.DescricaoProblemaDao;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@RequestScoped
public class TextosProntosService {

	@Inject
	private DescricaoProblemaDao dao;

	@Produces @RequestScoped
	public List<DescricaoProblema> getListaDeProblemas() {
		return dao.findAll();
	}

}
