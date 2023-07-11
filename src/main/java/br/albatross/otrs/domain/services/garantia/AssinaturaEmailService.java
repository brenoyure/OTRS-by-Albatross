package br.albatross.otrs.domain.services.garantia;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class AssinaturaEmailService {

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
