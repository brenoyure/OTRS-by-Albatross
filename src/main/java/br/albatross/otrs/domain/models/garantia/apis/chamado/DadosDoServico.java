package br.albatross.otrs.domain.models.garantia.apis.chamado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface DadosDoServico {

	@NotNull
	Integer getIdDoServico();

	@NotBlank
	String getNomeDoServico();

	void setIdDoServico(Integer id);

	void setNomeDoServico(String nomeDoServico);

}
