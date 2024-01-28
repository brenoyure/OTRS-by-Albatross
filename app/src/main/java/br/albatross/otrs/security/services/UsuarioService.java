package br.albatross.otrs.security.services;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.security.models.DadosParaAtualizacaoDeUsuarioDto;
import br.albatross.otrs.security.models.DadosParaCadastroDeUsuarioDto;
import br.albatross.otrs.security.models.DadosParaListagemDoUsuarioDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@ApplicationScoped
public class UsuarioService {
	
	@Inject
	private CadastroUsuarioService cadastroService;
	
	@Inject
	private UsuarioListagemService listagemService;

	public DadosParaListagemDoUsuarioDto cadastrarNovoUsuario(@Valid DadosParaCadastroDeUsuarioDto novosDados) {
		return cadastroService
				.cadastrarNovoUsuario(novosDados);

	}

	public DadosParaListagemDoUsuarioDto atualizarCadastro(@Valid DadosParaAtualizacaoDeUsuarioDto dadosAtualizados) {
		return cadastroService
				.atualizarCadastro(dadosAtualizados);
	}

	public List<DadosParaListagemDoUsuarioDto> getUsuarios() {
		return listagemService
				.listar();
	}

	public Optional<DadosParaListagemDoUsuarioDto> carregarPorId(@Positive int id) {
		return listagemService
				.buscarPorId(id);
	}

}
