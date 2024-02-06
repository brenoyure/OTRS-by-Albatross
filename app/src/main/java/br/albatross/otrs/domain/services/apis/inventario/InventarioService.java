package br.albatross.otrs.domain.services.apis.inventario;

import java.util.Optional;

public interface InventarioService {

	Optional<String> buscarNumeroDeSeriePeloBm(String bm);

}
