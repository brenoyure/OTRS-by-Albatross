package br.albatross.otrs.domain.services.clientes.validacoes;

import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosAtualizacaoCliente;
import br.albatross.otrs.repositories.cliente.ClienteRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;

/**
 * 
 * Validação responsável por verificar se já existe outro cliente com o nome, 
 * ou a descrição do cliente que será atualizado.
 *  
 * @author breno.brito
 */
@RequestScoped
public class ValidacaoAtualizacaoClienteDadosDuplicados implements ValidacaoAtualizacaoCliente {

    @Inject
    private ClienteRepository repository;

    @Override
    public void validar(DadosAtualizacaoCliente dadosDoCliente) {

        if (repository.existsByNomeAndNotById(dadosDoCliente.getNome(), dadosDoCliente.getId())) {
            throw new ValidationException("Já existe outro cliente cadastrado com o nome informado");
        }

        if (repository.existsByDescricaoAndNotById(dadosDoCliente.getDescricao(), dadosDoCliente.getId())) {
            throw new ValidationException("Já existe outro cliente cadastrado com a descrição informada");
        }

    }

}
