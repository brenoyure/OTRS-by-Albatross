package br.albatross.otrs.security.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public final class RoleDto {

	private final int id;
	private final String name;

	public RoleDto(Role roleEntity) {
		this.id = roleEntity.getId();
		this.name = roleEntity.getName();
	}

	@Override
	public String toString() {
		return this.name;
	}

}
