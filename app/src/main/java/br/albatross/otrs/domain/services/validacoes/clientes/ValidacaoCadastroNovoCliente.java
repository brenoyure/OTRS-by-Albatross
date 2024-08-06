package br.albatross.otrs.domain.services.validacoes.clientes;

import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosParaCadastroDeNovoCliente;

/**
 * 
 * Representa uma validação que podem ser executadas durante o cadastro de um novo cliente.
 * 
 * @author breno.brito
 * 
 */
public interface ValidacaoCadastroNovoCliente {

    void validar(DadosParaCadastroDeNovoCliente dadosDoNovoCliente);

}
