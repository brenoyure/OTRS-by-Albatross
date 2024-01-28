package br.albatross.otrs.security.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public final class RoleDto {

	private int id;
	private String name;

	public RoleDto(Role roleEntity) {
		this.id = roleEntity.getId();
		this.name = roleEntity.getName();
	}

}
