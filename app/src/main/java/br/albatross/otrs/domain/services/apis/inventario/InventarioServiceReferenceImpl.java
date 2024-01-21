package br.albatross.otrs.domain.services.apis.inventario;

import java.io.Serializable;
import java.util.Optional;

import br.albatross.otrs.domain.dao.apis.equipamentos.InventarioDao;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;

@ViewScoped
class InventarioServiceReferenceImpl implements Serializable, InventarioService {

	private static final long serialVersionUID = 1L;

	@Inject
	private InventarioDao dao;

	public Optional<String> buscarNumeroDeSeriePeloBm(String bm) {
		return dao.buscarNumeroDeSeriePeloBm(bm);
	}

}
