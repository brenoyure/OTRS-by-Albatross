package br.albatross.otrs.domain.models.garantia.entidades.email;

import java.io.File;

import br.albatross.otrs.domain.models.garantia.apis.email.DadosDoEnvio;
import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmailDeGarantiaImpl implements EmailDeGarantia {

	private static final long serialVersionUID = 1L;

	private String assunto;

	private String corpoDaMensagem;

	private File[] anexos;

	private DadosDoEnvio dadosDoEnvio;

	private SolicitacaoDeGarantia solicitacaoGarantia;

}
