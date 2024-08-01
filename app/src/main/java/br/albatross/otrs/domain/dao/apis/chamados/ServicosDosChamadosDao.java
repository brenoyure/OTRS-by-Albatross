package br.albatross.otrs.domain.dao.apis.chamados;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServico;

/**
 * 
 * Representa o contexto de persistência (DAO) com algum Sistema de Chamados, 
 * por exemplo, exibindo a lista de Serviços que os chamados podem possuir.
 * 
 * @author breno.brito
 * 
 */
public interface ServicosDosChamadosDao {

    List<DadosDoServico> listarServicosDisponiveis();

}
