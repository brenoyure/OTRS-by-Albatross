package br.albatross.otrs.domain.services.validacoes.clientes;

import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosCadastroCliente;
import br.albatross.otrs.repositories.cliente.ClienteRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.ValidationException;

/**
 * 
 * Validação responsável por verificar se já existe outro cliente com o nome, 
 * ou a descrição do cliente que será cadastrado.
 *  
 * @author breno.brito
 */
@RequestScoped
public class ValidacaoCadastroNovoClienteDadosDuplicados implements ValidacaoCadastroNovoCliente {

    @Inject
    private ClienteRepository repository;

    @Override
    public void validar(DadosCadastroCliente dadosDoNovoCliente) {

        if (repository.existsByNome(dadosDoNovoCliente.getNome())) {
            throw new ValidationException("Já existe outro cliente cadastrado com o nome informado");
        }

        if (repository.existsByDescricao(dadosDoNovoCliente.getDescricao())) {
            throw new ValidationException("Já existe outro cliente cadastrado com a descrição informada");
        }

    }

}
