package br.albatross.otrs.domain.models.garantia.apis.chamado;

import jakarta.validation.constraints.NotBlank;

public interface DadosDoResponsavelPeloChamado {

	@NotBlank
	String getLogin();

	@NotBlank
	String getNomeCompleto();

	void setLogin(String login);

}
