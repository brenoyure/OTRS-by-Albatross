package br.albatross.otrs.domain.services.apis.chamados;

import java.util.List;

import br.albatross.otrs.domain.dao.apis.chamados.ChamadosDao;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
class ServicoDeChamadosReferenceImpl implements ServicoDeChamados {

	@Inject
	private ChamadosDao dao;

	public List<DadosDoChamado> listarTodosOsChamadosAbertos() {
		return dao.findAllOpened();
	}

}
