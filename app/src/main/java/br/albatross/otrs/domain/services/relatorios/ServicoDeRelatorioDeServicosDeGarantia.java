package br.albatross.otrs.domain.services.relatorios;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.entidades.relatorios.RelatorioServicoDeGarantia;

/**
 * 
 * Responsável por retornar os dados de relatório das solicitações de garantia.
 * 
 * @author breno.brito
 */
public interface ServicoDeRelatorioDeServicosDeGarantia {

    List<RelatorioServicoDeGarantia> getRelatorioDosServicosDoFornecedor(int fornecedorId);

}
