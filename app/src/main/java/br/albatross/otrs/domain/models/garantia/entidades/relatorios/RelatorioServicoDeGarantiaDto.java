package br.albatross.otrs.domain.models.garantia.entidades.relatorios;

import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServico;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServicoDto;
import br.albatross.otrs.domain.models.garantia.apis.relatorios.RelatorioServicoDeGarantia;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode(of = "idDoServico")
public class RelatorioServicoDeGarantiaDto implements RelatorioServicoDeGarantia {

	private static final long serialVersionUID = 1L;

	private final long total;
	private final int idDoServico;
	private final String nomeDoServico;
	private final LocalDateTime dataEHoraDaUltimaSolicitacao;

	@Override
	public long getTotal() {
		return this.total;
	}

	@Override
	public DadosDoServico getDadosDoServico() {
		return new DadosDoServicoDto(this.idDoServico, this.nomeDoServico);
		
	}

	@Override
	public LocalDateTime getDataEHoraDaUltimaSolicitacao() {
		return this.dataEHoraDaUltimaSolicitacao;
	}

}
