package br.albatross.otrs.domain.services.garantia;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class AssinaturaEmailService {

	public String getCorpoDoEmailComAssinatura(SolicitacaoDeGarantia solicitacao) {

	    String nomeEDescricaoDoCliente = 
	            solicitacao.getDadosDoCliente().getNome() + " - " + solicitacao.getDadosDoCliente().getDescricao();

	    String numeroDeSerie = 
	            solicitacao.getNumeroDeSerie();

	    String numerosTelefonicosDoSolicitante = 
	            solicitacao.getDadosDoCliente().getNumerosParaContato();

	    String emailsDoSolicitante =
	            solicitacao.getDadosDoCliente().getEmails();

		return String.format("""
Prezados,
Falamos do %s,
Segue em anexo o formulário preenchido para abertura de solicitação de garantia para o equipamento: %s.

O formulário possui as informações necessárias sobre a solicitação, como, a descrição detalhada do problema,
horários disponíveis, como também, os dados de endereço.

Atenciosamente,
--
Serviço de Tecnologia da Informação
%s
%s

				""",
				nomeEDescricaoDoCliente,
				numeroDeSerie,
				numerosTelefonicosDoSolicitante,
				emailsDoSolicitante);
	}

	public String getCorpoDoEmailComAssinatura(String numeroDeSerie, String login, String fullName) {
		return String.format("""
Prezados,
Segue o formulário preenchido para abertura de chamado para o equipamento: %s.


Atenciosamente,
--
%s
Técnico de Suporte Nível 1 
(55) 4433-2142
%s@albatross.com

				""", numeroDeSerie, fullName,login);
	}

	public String getCorpoDoEmailComAssinatura(String numeroDeSerie, String login, String firstName, String lastName) {
		return String.format("""
Prezados,
Segue o formulário preenchido para abertura de chamado para o equipamento: %s.


Atenciosamente,
--
%s %s
Técnico de Suporte Nível 1 
(55) 4433-2142
%s@albatross.com

				""", numeroDeSerie, firstName, lastName, login);
	}

}
