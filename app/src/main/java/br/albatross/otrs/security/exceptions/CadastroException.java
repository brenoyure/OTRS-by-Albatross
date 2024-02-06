package br.albatross.otrs.security.exceptions;

import lombok.Getter;

@Getter
public class CadastroException extends UsuarioServiceException {

	private static final long serialVersionUID = 1L;

	public CadastroException(String mensagem, String mensagemDetalhada) {
		super(mensagem, mensagemDetalhada);
	}
	
}
