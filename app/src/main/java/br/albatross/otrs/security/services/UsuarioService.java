package br.albatross.otrs.security.services;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.security.daos.UsersDao;
import br.albatross.otrs.security.exceptions.CadastroException;
import br.albatross.otrs.security.models.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioService {

	@Inject
	private UsersDao dao;

	@Inject
	private PasswordService passwordService;

	public void cadastrarNovoUsuario(User user) {
		if (dao.existsByUsername(user.getUsername())) {
			throw new CadastroException("Usuário já existente", "Já existe um usuário com o nome informado, cadastro não realizado.");			
		}

		final String hashedPassword = passwordService.generateHashing(user.getPassword());
		user.setPassword(hashedPassword);
		dao.persist(user);
	}

	@Transactional
	public void atualizarCadastro(User user) {
		System.out.println("At C");
		final String hashedPassword = passwordService.generateHashing(user.getPassword());
		System.out.println("Dp C");
		user.setPassword(hashedPassword);		
		dao.atualizar(user);
	}	

	public List<User> getUsuarios() {
		return dao.findAll();
	}

	public Optional<User> carregarPorId(int id) {
		return dao.findById(id);
	}

}
