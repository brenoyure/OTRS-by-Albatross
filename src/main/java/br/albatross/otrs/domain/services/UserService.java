package br.albatross.otrs.domain.services;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.domain.dao.UserDAO;
import br.albatross.otrs.domain.models.User;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class UserService {

	@Inject
	private UserDAO dao;

	public Optional<User> buscaPorId(Integer id) {
		return dao.findById(id);
	}

	public List<User> listarTodos() {
		return dao.findAll();
	}

}
