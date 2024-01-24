package br.albatross.otrs.security.beans;

import java.io.Serializable;

import br.albatross.otrs.security.exceptions.CadastroException;
import br.albatross.otrs.security.models.User;
import br.albatross.otrs.security.services.UsuarioService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class CadastroUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private UsuarioService usuarioService;

	@Getter @Setter
	private User user;

	@Getter @Setter
	private int id;

	@PostConstruct
	void init() {
		user = new User();
	}

	@Transactional
	public void cadastrarUsuario() {
		try {
			usuarioService.cadastrarNovoUsuario(user);
			facesContext.addMessage(null, new FacesMessage(String.format("Usuário %s cadastrado com sucesso.", user.getUsername())));
		} catch (CadastroException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMensagem(), e.getMensagemDetalhada()));
		}
		finally {
			user = new User();
		}
	}

	@Transactional
	public void atualizarCadastro() {
		usuarioService.atualizarCadastro(user);
		facesContext.addMessage(null, new FacesMessage(String.format("Cadastro do Usuário %s atualizado com sucesso.", user.getUsername())));
	}

	@Transactional
	public void carregarUsuarioPeloId() {
		usuarioService.carregarPorId(id).ifPresent(this::setUser);
	}

}
