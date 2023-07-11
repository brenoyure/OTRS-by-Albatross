package br.albatross.otrs.domain.models.garantia.apis.solicitacao;

import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface SolicitacaoGarantia extends Serializable {

	@NotBlank
	String getNumeroDeSerie();

	@NotBlank
	String getDescricaoDoProblema();

	@NotNull
	DadosDoChamado getChamado();

	@NotNull
	EmailDeGarantia getEmailDeGarantia();

	void setNumeroDeSerie(String numeroDeSerie);

	void setDescricaoDoProblema(String descricaoDoProblema);

	void setChamado(DadosDoChamado chamado);

	void setEmailDeGarantia(EmailDeGarantia emailDeGarantia);

}
