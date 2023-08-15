package br.albatross.otrs.domain.models.garantia.apis.chamado;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface DadosDoChamado extends Serializable {

	@NotNull
	Long getId();

	@NotBlank
	String getNumeroDoChamado();

	@NotBlank
	String getTitulo();	

	@NotNull
	DadosDoServico getDadosDoServico();

	@NotNull
	DadosDoUsuarioCliente getDadosDoUsuarioCliente();

}
