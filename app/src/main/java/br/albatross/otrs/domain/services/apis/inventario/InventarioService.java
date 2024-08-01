package br.albatross.otrs.domain.services.apis.inventario;

import java.util.Optional;

/**
 * 
 * <p>Representa o Serviço de Inventário, por exemplo, para buscar,
 * dados de um equipamento.</p>
 * 
 * <p>O identificador pode ser um número de patrimônio ou qualquer outro 
 * identificador que o serviço pode utilizar para retornar o Número de Série do Equipamento.</p>
 * 
 * @author breno.brito
 */
public interface InventarioService {

	Optional<String> buscarNumeroDeSeriePeloIdentificadorUnicoDoEquipamento(String identificador);

}
