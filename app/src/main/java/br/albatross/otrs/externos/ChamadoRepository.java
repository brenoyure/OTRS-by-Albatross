package br.albatross.otrs.externos;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;

/**
 * 
 * Representa o contexto de persistência (DAO) com o Sistema de Chamados.
 * 
 * @author breno.brito
 * 
 */
public interface ChamadoRepository {

    /**
     * 
     * Retorna uma lista de chamados filtrada pelo ID do Serviço.
     * 
     * @param serviceIds
     * @return
     */
	List<DadosDoChamado> findByService(List<Integer> servicesIds);

}
