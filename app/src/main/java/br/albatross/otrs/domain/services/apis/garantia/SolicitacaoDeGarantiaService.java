package br.albatross.otrs.domain.services.apis.garantia;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.models.garantia.entidades.solicitacao.DadosParaNovaSolicitacaoDeGarantia;
import jakarta.validation.Valid;

/**
 * 
 * Serviço responsável por dar início a abertura de Solicitação de Garantia
 * a partir dos dados básicos recebidos por um form, por exemplo.
 * 
 * @author breno.brito
 * 
 */
public interface SolicitacaoDeGarantiaService {

    SolicitacaoDeGarantia criarNovaSolicitacao(@Valid DadosParaNovaSolicitacaoDeGarantia solicitacao);

}
