package br.albatross.otrs.domain.services.apis.inventario;

import java.util.Optional;

import br.albatross.otrs.domain.dao.apis.equipamentos.InventarioDao;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

/**
 * 
 * Representa o Serviço de Inventário, por exemplo, para buscar,
 * dados de um equipamento, utilizando o Sistema de Chamados Otrs/Znuny
 * 
 * @author breno.brito
 */
@RequestScoped
public class InventarioServiceOtrsImpl implements InventarioService {

	@Inject
	private InventarioDao dao;

	public Optional<String> buscarNumeroDeSeriePeloIdentificadorUnicoDoEquipamento(String bm) {
		return dao.buscarNumeroDeSeriePeloBm(bm);
	}

}
