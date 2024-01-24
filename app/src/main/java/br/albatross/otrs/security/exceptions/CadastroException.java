package br.albatross.otrs.security.exceptions;

import lombok.Getter;

@Getter
public class CadastroException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String mensagem;
	private final String mensagemDetalhada;

	public CadastroException(String mensagem, String mensagemDetalhada) {
		this.mensagemDetalhada = mensagemDetalhada;
		this.mensagem = mensagem;
	}
	
}
