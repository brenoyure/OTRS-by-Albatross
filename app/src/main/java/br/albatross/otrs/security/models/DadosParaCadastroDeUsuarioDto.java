package br.albatross.otrs.security.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosParaCadastroDeUsuarioDto {

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@NotEmpty
	private List<Integer> rolesIds = new ArrayList<>();

}