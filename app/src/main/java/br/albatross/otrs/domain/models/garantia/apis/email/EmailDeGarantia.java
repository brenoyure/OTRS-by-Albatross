package br.albatross.otrs.domain.models.garantia.apis.email;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import jakarta.validation.constraints.NotNull;

/**
 * <p>Representa um E-mail de Garantia.</p>
 * Esta interface extende a <code><strong>Serializable</strong></code> para que possa ser enviado, por exemplo, para uma fila de mensagens como a <code>jakarta.jms.Queue</code>.
 */
public interface EmailDeGarantia extends Email {

	/**
	 * 
	 * @return A Solicitação de Garantia a qual este e-mail pertence (<strong>Não</strong> podendo ficar nulo).
	 */
	@NotNull
	SolicitacaoDeGarantia getSolicitacaoGarantia();

	/**
	 * 
	 * Define a Solicitação de Garantia a qual este e-mail pertence. (<strong>Não</strong> podendo ficar nulo).
	 */
	void setSolicitacaoGarantia(SolicitacaoDeGarantia solicitacaoGarantia);

}
