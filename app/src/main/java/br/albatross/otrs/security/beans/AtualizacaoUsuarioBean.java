package br.albatross.otrs.security.beans;

import java.io.Serializable;

import br.albatross.otrs.security.models.DadosParaAtualizacaoDeUsuarioDto;
import br.albatross.otrs.security.services.UsuarioService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class AtualizacaoUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private UsuarioService usuarioService;
	
	@Getter @Setter
	private DadosParaAtualizacaoDeUsuarioDto dados;

	@Transactional
	public void atualizarCadastro() {
		var listagemDto = usuarioService.atualizarCadastro(dados);
		facesContext.addMessage(null,
				new FacesMessage(String.format("Cadastro do Usuário %s atualizado com sucesso.", listagemDto.getUsername())));
	}

	public void carregarUsuarioPeloId(int id) {
		usuarioService.carregarPorId(id).ifPresent(dto -> {
			this.dados = new DadosParaAtualizacaoDeUsuarioDto(dto);
		});
	}

}
