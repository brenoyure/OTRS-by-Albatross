package br.albatross.otrs.security.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "id")
public final class DadosParaListagemDoUsuarioDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int id;
	private final String username;
	private final Collection<RoleDto> roles;

	public DadosParaListagemDoUsuarioDto(User userEntity) {
		
		this.id = userEntity.getId();
		this.username = userEntity.getUsername();

		this.roles = new HashSet<>(userEntity.getRoles().size());

		userEntity
			.getRoles()
			.stream()
			.map(RoleDto::new)
			.forEach(roles::add);

	}

}
