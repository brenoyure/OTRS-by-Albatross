package br.albatross.otrs.domain.dao.apis.chamados;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;

/**
 * 
 * Representa o contexto de persistência (DAO) com o Sistema de Chamados.
 * 
 * @author breno.brito
 * 
 */
public interface ChamadosDao {

    /**
     * 
     * Retorna uma lista de chamados filtrada pelo ID do Serviço.
     * 
     * @param serviceIds
     * @return
     */
	List<DadosDoChamado> findByService(List<Integer> servicesIds);

	Optional<DadosDoChamado> findById(long id);

}
