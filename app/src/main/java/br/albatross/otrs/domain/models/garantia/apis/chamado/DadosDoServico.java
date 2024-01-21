package br.albatross.otrs.domain.models.garantia.apis.chamado;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface DadosDoServico extends Serializable {

	@NotNull
	Integer getIdDoServico();

	@NotBlank
	String getNomeDoServico();

}
