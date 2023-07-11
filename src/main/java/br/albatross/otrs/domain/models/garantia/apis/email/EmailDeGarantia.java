package br.albatross.otrs.domain.models.garantia.apis.email;

import java.io.File;
import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoGarantia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface EmailDeGarantia extends Serializable {

	@NotBlank
	String getAssunto();

	@NotEmpty
	File[] getAnexos();

	@NotBlank
	String getCorpoDaMensagem();

	@NotNull
	DadosDoEnvio getDadosDoEnvio();

	@NotNull
	SolicitacaoGarantia getSolicitacaoGarantia();

	void setAssunto(String assunto);

	void setAnexos(File[] anexos);

	void setCorpoDaMensagem(String corpoDaMensagem);

	void setDadosDoEnvio(DadosDoEnvio dadosDoEnvio);

	void setSolicitacaoGarantia(SolicitacaoGarantia solicitacaoGarantia);

}
