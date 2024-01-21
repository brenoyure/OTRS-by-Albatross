package br.albatross.otrs.domain.models.garantia.apis.chamado;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class DadosDoChamadoDto implements DadosDoChamado {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String numeroDoChamado;

	private String titulo;

	private Integer idDoServico;

	private String nomeDoServico;

	private String nomeDoUsuario;

	@Override
	public @NotNull DadosDoServico getDadosDoServico() {
		return new DadosDoServico() {

			private static final long serialVersionUID = 1L;

			@Override
			public @NotBlank String getNomeDoServico() {
				return nomeDoServico;
			}

			@Override
			public @NotNull Integer getIdDoServico() {
				return idDoServico;
			}
		};
	}

	@Override
	public @NotNull DadosDoUsuarioCliente getDadosDoUsuarioCliente() {
		return () -> nomeDoUsuario;
	}

}
