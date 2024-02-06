package br.albatross.otrs.domain.models.garantia.apis.relatorios;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServico;

public interface RelatorioServicoDeGarantia extends Serializable {

	long getTotal();
	DadosDoServico getDadosDoServico();
	LocalDateTime getDataEHoraDaUltimaSolicitacao();

}
