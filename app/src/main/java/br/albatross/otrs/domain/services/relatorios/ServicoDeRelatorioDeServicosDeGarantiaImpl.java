package br.albatross.otrs.domain.services.relatorios;

import java.util.List;

import br.albatross.otrs.domain.dao.apis.relatorios.RelatorioServicoDeGarantiaDao;
import br.albatross.otrs.domain.models.garantia.apis.relatorios.RelatorioServicoDeGarantia;
import br.albatross.otrs.domain.services.apis.relatorios.ServicoDeRelatorioDeServicosDeGarantia;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class ServicoDeRelatorioDeServicosDeGarantiaImpl implements ServicoDeRelatorioDeServicosDeGarantia {

	@Inject
	private RelatorioServicoDeGarantiaDao dao;

	@Override
	public List<RelatorioServicoDeGarantia> getRelatorio() {
		return dao.getRelatorio();
	}

	@Override
	public List<RelatorioServicoDeGarantia> getRelatorioMensal() {
		return dao.getRelatorioMensal();
	}

	@Override
	public List<RelatorioServicoDeGarantia> getRelatorioAnual() {
		return dao.getRelatorioAnual();
	}

}
