package br.albatross.otrs.security.services;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.security.daos.UsersDao;
import br.albatross.otrs.security.models.DadosParaListagemDoUsuarioDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuarioListagemService {

	@Inject
	private UsersDao dao;

	public List<DadosParaListagemDoUsuarioDto> listar() {
		return dao.findAll();
	}

	public Optional<DadosParaListagemDoUsuarioDto> buscarPorId(int id) {
		return dao.findById(id);
	}

}
