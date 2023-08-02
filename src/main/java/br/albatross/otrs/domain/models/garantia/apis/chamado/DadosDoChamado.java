package br.albatross.otrs.domain.models.garantia.apis.chamado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface DadosDoChamado {

	@NotNull
	Long getId();

	@NotBlank
	String getNumeroDoChamado();

	@NotBlank
	String getTitulo();	

	@NotNull
	DadosDoServico getDadosDoServico();

	@NotNull
	DadosDoResponsavelPeloChamado getDadosDoResponsavelPeloChamado();

	@NotNull
	DadosDoUsuarioCliente getDadosDoUsuarioCliente();

	void setId(Long id);

	void setNumeroDoChamado(String numeroDoChamado);

	void setTitulo(String titulo);

	void setDadosDoServico(DadosDoServico servico);

	void setDadosDoResponsavel(DadosDoResponsavelPeloChamado dadosDoResponsavel);

}
