package br.albatross.otrs.domain.models.garantia.entidades.solicitacao;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SolicitacaoDeGarantiaImpl implements SolicitacaoDeGarantia {

	private static final long serialVersionUID = 1L;

	private String numeroDeSerie;

	private String descricaoDoProblema;

	private Number idDoServicoDoChamadoDoOtrs;

	private EmailDeGarantia emailDeGarantia;

	private DadosDoChamado chamado;

}
