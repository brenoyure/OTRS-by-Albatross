package br.albatross.otrs.security.services;

import br.albatross.otrs.security.daos.RolesDao;
import br.albatross.otrs.security.daos.UsersDao;
import br.albatross.otrs.security.exceptions.CadastroException;
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
		novoUsuario.setRoles(rolesDao.getRoles(dados.getRolesIds()));

		return dao
				.persist(novoUsuario);

	}

	public DadosParaListagemDoUsuarioDto atualizarCadastro(@Valid DadosParaAtualizacaoDeUsuarioDto dadosAtualizados) {

		final String hashedPassword = passwordService.generateHashing(dadosAtualizados.getPassword());

		var usuarioAtualizado = new User();
		usuarioAtualizado.setId(dadosAtualizados.getId());
		usuarioAtualizado.setUsername(dadosAtualizados.getUsername());
		usuarioAtualizado.setPassword(hashedPassword);
		usuarioAtualizado.setRoles(rolesDao.getRoles(dadosAtualizados.getRolesIds()));

		return dao
				.atualizar(usuarioAtualizado);

	}

}
