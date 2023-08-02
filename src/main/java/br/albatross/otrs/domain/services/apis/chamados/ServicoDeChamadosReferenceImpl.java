package br.albatross.otrs.domain.services.apis.chamados;

import java.util.List;

import br.albatross.otrs.domain.dao.apis.chamados.ChamadosDao;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import jakarta.enterprise.inject.Produces;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;

class ServicoDeChamadosReferenceImpl implements ServicoDeChamados {

	@Inject
	private ChamadosDao dao;

	@Produces @ViewScoped
	public List<DadosDoChamado> listarTodosOsChamadosAbertos() {
		return dao.findAllOpened();
	}

}
