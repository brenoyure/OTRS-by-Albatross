package br.albatross.otrs.cdi;

import java.util.ArrayList;
import java.util.List;

import br.albatross.otrs.domain.services.validacoes.clientes.ValidacaoAtualizacaoCliente;
import br.albatross.otrs.domain.services.validacoes.clientes.ValidacaoCadastroNovoCliente;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@RequestScoped
public class ValidacoesCadastroClienteFactoryBean {

    @Inject
    private Instance<ValidacaoCadastroNovoCliente> validacoesCadastroInstance;

    @Inject
    private Instance<ValidacaoAtualizacaoCliente> validacoesAtualizacaoInstance;    

    @Produces
    public List<ValidacaoCadastroNovoCliente> getValidacoesCadastro() {
        List<ValidacaoCadastroNovoCliente> validacoes = new ArrayList<>();
        validacoesCadastroInstance.forEach(validacoes::add);
        return validacoes;
    }

    @Produces
    public List<ValidacaoAtualizacaoCliente> getValidacoesAtualizacao() {
        List<ValidacaoAtualizacaoCliente> validacoes = new ArrayList<>();
        validacoesAtualizacaoInstance.forEach(validacoes::add);
        return validacoes;
    }    

}
