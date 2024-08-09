package br.albatross.otrs.domain.services.clientes.validacoes;

import br.albatross.otrs.domain.models.cliente.DadosAtualizacaoCliente;

/**
 * 
 * Representa uma validação que podem ser executadas durante o cadastro de um novo cliente.
 * 
 * @author breno.brito
 * 
 */
public interface ValidacaoAtualizacaoCliente {

    void validar(DadosAtualizacaoCliente dadosDoCliente);

}
