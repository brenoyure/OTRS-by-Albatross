package br.albatross.otrs.domain.dao.apis.equipamentos;

import java.util.Optional;

public interface InventarioDao {

	Optional<String> buscarNumeroDeSeriePeloBm(String bm);

}
