package br.albatross.otrs.security.models;

import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "id")
public final class DadosParaListagemDoUsuarioDto {

	private final int id;
	private final String username;
	private final Collection<RoleDto> roles;

	public DadosParaListagemDoUsuarioDto(User userEntity) {

		this.id = userEntity.getId();
		this.username = userEntity.getUsername();
		this.roles = userEntity.getRoles().stream().map(RoleDto::new).toList();

	}

}