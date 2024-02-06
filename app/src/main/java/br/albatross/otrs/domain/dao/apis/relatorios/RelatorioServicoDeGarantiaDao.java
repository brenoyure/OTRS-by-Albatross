package br.albatross.otrs.domain.dao.apis.relatorios;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.apis.relatorios.RelatorioServicoDeGarantia;

public interface RelatorioServicoDeGarantiaDao {

	List<RelatorioServicoDeGarantia> getRelatorio();

	List<RelatorioServicoDeGarantia> getRelatorioMensal();

	List<RelatorioServicoDeGarantia> getRelatorioAnual();

}
