package br.albatross.otrs.security.exceptions;

import lombok.Getter;

@Getter
public class ListagemException extends UsuarioServiceException {

	private static final long serialVersionUID = 1L;

	public ListagemException(String mensagem, String mensagemDetalhada) {
		super(mensagem, mensagemDetalhada);
	}

}
