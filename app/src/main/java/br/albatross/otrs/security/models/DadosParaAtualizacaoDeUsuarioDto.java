package br.albatross.otrs.security.models;

import static java.util.stream.Collectors.toList;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class DadosParaAtualizacaoDeUsuarioDto extends DadosParaCadastroDeUsuarioDto {

	@Positive
	private int id;

	public DadosParaAtualizacaoDeUsuarioDto(User userEntity) {
		super(userEntity.getUsername(), null, userEntity.getRoles().stream().map(Role::getId).collect(toList()));
		this.id = userEntity.getId();
	}

	public DadosParaAtualizacaoDeUsuarioDto(DadosParaListagemDoUsuarioDto dtoListagem) {
		super(dtoListagem.getUsername(), null, dtoListagem.getRoles().stream().map(RoleDto::getId).collect(toList()));
		this.id = dtoListagem.getId();
	}

}
