package br.albatross.otrs.security.beans;

import java.io.Serializable;

import br.albatross.otrs.security.exceptions.CadastroException;
import br.albatross.otrs.security.models.DadosParaCadastroDeUsuarioDto;
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
	private DadosParaCadastroDeUsuarioDto dadosDeCadastro;

	@PostConstruct
	void init() {
		dadosDeCadastro = new DadosParaCadastroDeUsuarioDto();
	}

	@Transactional
	public void cadastrarUsuario() {

		try {

			usuarioService.cadastrarNovoUsuario(dadosDeCadastro);
			facesContext.addMessage(null, new FacesMessage(String.format("Usuário %s cadastrado com sucesso.", dadosDeCadastro.getUsername())));

		} catch (CadastroException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMensagem(), e.getMensagemDetalhada()));
			dadosDeCadastro = new DadosParaCadastroDeUsuarioDto();

		}


	}


}
