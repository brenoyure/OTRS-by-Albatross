package br.albatross.otrs.security.services;

import br.albatross.otrs.security.daos.RolesDao;
import br.albatross.otrs.security.daos.UsersDao;
import br.albatross.otrs.security.exceptions.CadastroException;
import br.albatross.otrs.security.exceptions.ListagemException;
import br.albatross.otrs.security.models.DadosParaAtualizacaoDeUsuarioDto;
import br.albatross.otrs.security.models.DadosParaCadastroDeUsuarioDto;
import br.albatross.otrs.security.models.DadosParaListagemDoUsuarioDto;
import br.albatross.otrs.security.models.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class CadastroUsuarioService {

	@Inject
	private UsersDao dao;

	@Inject
	private RolesDao rolesDao;

	@Inject
	private PasswordService passwordService;

	public DadosParaListagemDoUsuarioDto cadastrarNovoUsuario(@Valid DadosParaCadastroDeUsuarioDto dados) {

		if (dao.existsByUsername(dados.getUsername())) {
			throw new CadastroException("Usuário já existente", "Já existe um usuário com o nome informado, cadastro não realizado.");			
		}

		final String hashedPassword = passwordService.generateHashing(dados.getPassword());

		var novoUsuario = new User();
		novoUsuario.setUsername(dados.getUsername());
		novoUsuario.setPassword(hashedPassword);
		
		rolesDao.getRoles(dados.getRolesIds()).stream().forEach(novoUsuario::addRole);

		return dao
				.persist(novoUsuario);

	}

	public DadosParaListagemDoUsuarioDto atualizarCadastro(@Valid DadosParaAtualizacaoDeUsuarioDto dadosAtualizados) {

		if (dao.existsByUsernameForMerge(dadosAtualizados.getId(), dadosAtualizados.getUsername())) {
			throw new CadastroException("Usuário já existente", "Já existe um usuário com o nome informado, atualização de cadastro não realizado.");
		}

		final String hashedPassword = passwordService.generateHashing(dadosAtualizados.getPassword());

		var usuarioAtualizado = new User();
		usuarioAtualizado.setId(dadosAtualizados.getId());
		usuarioAtualizado.setUsername(dadosAtualizados.getUsername());
		usuarioAtualizado.setPassword(hashedPassword);

		rolesDao.getRoles(dadosAtualizados.getRolesIds()).stream().forEach(usuarioAtualizado::addRole);

		return dao
				.atualizar(usuarioAtualizado);

	}

	public void excluirPorId(int id) {

		if (id == 1) {
			throw new CadastroException("Impossível Excluir Usuário", "Exclusão do Usuário Administrador não permitida.");
		}

		dao
			.getReference(id)
			.ifPresentOrElse(dao::remover, () -> {
				throw new ListagemException("Usuário Não Encontrado", "Usuário com o ID informado não encontrado, exclusão não realizada");

			});

	}

}
