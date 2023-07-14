package br.albatross.otrs.domain.models.garantia.apis.email;

import java.io.File;
import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoGarantia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <p>Representa um E-mail de Garantia.</p>
 * Esta interface extende a <code><strong>Serializable</strong></code> para que possa ser enviado, por exemplo, para uma fila de mensagens como a <code>jakarta.jms.Queue</code>.
 */
public interface EmailDeGarantia extends Serializable {

	/**
	 * 
	 * @return O assunto do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
	@NotBlank
	String getAssunto();

	/**
	 * 
	 * @return O(s) anexo(s) do e-mail (<strong>Não</strong> podendo ficar nulo ou vazio).
	 */
	@NotEmpty
	File[] getAnexos();

	/**
	 * 
	 * @return O corpo da mensagem do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
	@NotBlank
	String getCorpoDaMensagem();

	/**
	 * 
	 * @return Os Dados do Envio (como remetente, destinatário...) do e-mail (<strong>Não</strong> pode ficar nulo).
	 */
	@NotNull
	DadosDoEnvio getDadosDoEnvio();

	/**
	 * 
	 * @return A Solicitação de Garantia a qual este e-mail pertence (<strong>Não</strong> podendo ficar nulo).
	 */
	@NotNull
	SolicitacaoGarantia getSolicitacaoGarantia();

	/**
	 * 
	 * Define assunto do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
	void setAssunto(String assunto);

	/**
	 * 
	 * Define o(s) anexo(s) do e-mail (<strong>Não</strong> podendo ficar nulo ou vazio).
	 */
	void setAnexos(File[] anexos);

	/**
	 * 
	 * Define o corpo da mensagem do e-mail (<strong>Não</strong> podendo ficar em branco).
	 */
	void setCorpoDaMensagem(String corpoDaMensagem);

	/**
	 * 
	 * Define os Dados do Envio (como remetente, destinatário...) do e-mail (<strong>Não</strong> podendo ficar nulo).
	 */
	void setDadosDoEnvio(DadosDoEnvio dadosDoEnvio);

	/**
	 * 
	 * Define a Solicitação de Garantia a qual este e-mail pertence. (<strong>Não</strong> podendo ficar nulo).
	 */
	void setSolicitacaoGarantia(SolicitacaoGarantia solicitacaoGarantia);

}
