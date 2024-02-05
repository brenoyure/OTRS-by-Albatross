package br.albatross.otrs.security.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.security.exceptions.UsuarioServiceException;
import br.albatross.otrs.security.models.DadosParaListagemDoUsuarioDto;
import br.albatross.otrs.security.services.UsuarioService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @ViewScoped
public class ListaUsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private UsuarioService service;

	@Getter
	private List<DadosParaListagemDoUsuarioDto> usuarios;

	@PostConstruct
	void init() {
		usuarios = service.getUsuarios();
	}

	@Transactional
	public void excluirUsuario(DadosParaListagemDoUsuarioDto usuario) {

		try {
			service.excluir(usuario.getId());
			usuarios.remove(usuario);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.format("Usuário %s excluído com sucesso", usuario.getUsername()), null));
		} catch(UsuarioServiceException e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMensagem(), e.getMensagemDetalhada()));

		}

	}

}
