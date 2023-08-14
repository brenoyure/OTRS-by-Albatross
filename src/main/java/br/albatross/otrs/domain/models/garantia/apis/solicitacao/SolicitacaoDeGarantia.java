package br.albatross.otrs.domain.models.garantia.apis.solicitacao;

import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.models.garantia.apis.problemas.DescricaoProblema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Representa uma Solicitação de Garantia que pode ser aberta para algum fornecedor. 
 */
public interface SolicitacaoDeGarantia extends Serializable {

	/**
	 * 
	 * @return O Nº de Série do Equipamento (ex. Computador, Monitor...) (<strong>Não</strong> podendo ficar em branco).
	 */
	@NotBlank
	String getNumeroDeSerie();

	/**
	 * 
	 * @return A Descrição do Problema (<strong>Não</strong> podendo ficar em branco).
	 */
	@NotNull
	DescricaoProblema getDescricaoDoProblema();

	/**
	 * 
	 * @return Os Dados do Chamado a qual esta solicitação pertence (<strong>Não</strong> podendo ficar nulo).
	 */
	@NotNull
	DadosDoChamado getChamado();

	/**
	 * 
	 * @return O E-mail que será enviado para o fabricante/fornecedor do equipamento (<strong>Não</strong> podendo ficar nulo).
	 */
	@NotNull
	EmailDeGarantia getEmailDeGarantia();

	/**
	 * 
	 * Define o Nº de Série do Equipamento (ex. Computador, Monitor...) (<strong>Não</strong> podendo ficar em branco).
	 */
	void setNumeroDeSerie(String numeroDeSerie);

	/**
	 * 
	 * Define a Descrição do Problema (<strong>Não</strong> podendo ficar em branco).
	 */
	void setDescricaoDoProblema(DescricaoProblema descricaoDoProblema);

	/**
	 * 
	 * Define qual os Dados do Chamado a qual esta Solicitação pertence (<strong>Não</strong> podendo ficar em nulo).
	 */
	void setChamado(DadosDoChamado chamado);

	/**
	 * 
	 * Define o E-mail que será enviado para o fabricante/fornecedor do equipamento (<strong>Não</strong> podendo ficar nulo).
	 */
	void setEmailDeGarantia(EmailDeGarantia emailDeGarantia);

}
