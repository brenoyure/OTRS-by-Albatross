package br.albatross.otrs.security.daos;

import java.util.List;
import java.util.Optional;

import org.hibernate.jpa.AvailableHints;

import br.albatross.otrs.security.models.DadosParaListagemDoUsuarioDto;
import br.albatross.otrs.security.models.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class UsersDao {

	@PersistenceContext(unitName = "otrsdb_textos_prontos")
	private EntityManager entityManager;

	public DadosParaListagemDoUsuarioDto persist(User user) {
		entityManager.persist(user);
		return new DadosParaListagemDoUsuarioDto(user);
	}

	public boolean existsByUsername(String username) {
		try {
			return entityManager
					.createQuery("SELECT EXISTS(SELECT u FROM User u WHERE u.username = ?1)", Boolean.class)
					.setParameter(1, username)
					.setHint(AvailableHints.HINT_CACHEABLE, true)
					.getSingleResult();
		} catch (NoResultException e) {	return false; }
	}

	public List<DadosParaListagemDoUsuarioDto> findAll() {
		return entityManager
				.createQuery("SELECT new br.albatross.otrs.security.models.DadosParaListagemDoUsuarioDto(u) FROM User u", DadosParaListagemDoUsuarioDto.class)
				.setHint(AvailableHints.HINT_CACHEABLE, true)
				.getResultList();
	}

	public Optional<DadosParaListagemDoUsuarioDto> findById(int id) {

		try {

			return Optional.of(entityManager
								.createQuery("SELECT new br.albatross.otrs.security.models.DadosParaListagemDoUsuarioDto(u) FROM User u WHERE u.id = ?1", DadosParaListagemDoUsuarioDto.class)
								.setParameter(1, id)
								.setHint(AvailableHints.HINT_CACHEABLE, true)
								.getSingleResult());

		} catch (NoResultException e) { return Optional.empty(); }

	}

	public DadosParaListagemDoUsuarioDto atualizar(User user) {
		user = entityManager.merge(user);
		return new DadosParaListagemDoUsuarioDto(user);

	}

}
