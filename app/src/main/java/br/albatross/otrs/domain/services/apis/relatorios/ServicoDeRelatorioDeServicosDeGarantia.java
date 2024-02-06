package br.albatross.otrs.domain.services.apis.relatorios;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.apis.relatorios.RelatorioServicoDeGarantia;

public interface ServicoDeRelatorioDeServicosDeGarantia {

	List<RelatorioServicoDeGarantia> getRelatorio();
	List<RelatorioServicoDeGarantia> getRelatorioMensal();
	List<RelatorioServicoDeGarantia> getRelatorioAnual();

}
