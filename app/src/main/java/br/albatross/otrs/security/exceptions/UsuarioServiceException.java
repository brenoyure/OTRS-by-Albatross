package br.albatross.otrs.security.exceptions;

import lombok.Getter;

@Getter
public class UsuarioServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String mensagem;
	private final String mensagemDetalhada;

	public UsuarioServiceException(String mensagem, String mensagemDetalhada) {
		this.mensagemDetalhada = mensagemDetalhada;
		this.mensagem = mensagem;
	}
}
