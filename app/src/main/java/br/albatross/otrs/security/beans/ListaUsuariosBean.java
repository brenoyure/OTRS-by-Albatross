package br.albatross.otrs.security.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.security.models.User;
import br.albatross.otrs.security.services.UsuarioService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @ViewScoped
public class ListaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService service;

	@Getter
	private List<User> usuarios;

	@PostConstruct
	void init() {
		usuarios = service.getUsuarios();
	}
	
}
