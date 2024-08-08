package br.albatross.otrs.domain.services.clientes.validacoes;

import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosCadastroCliente;

/**
 * 
 * Representa uma validação que podem ser executadas durante o cadastro de um novo cliente.
 * 
 * @author breno.brito
 * 
 */
public interface ValidacaoCadastroNovoCliente {

    void validar(DadosCadastroCliente dadosDoNovoCliente);

}
