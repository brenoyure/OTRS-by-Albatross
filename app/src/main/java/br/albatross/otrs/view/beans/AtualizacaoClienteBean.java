package br.albatross.otrs.view.beans;

import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosParaAtualizacaoCadastralDoCliente;
import br.albatross.otrs.domain.services.apis.clientes.ClientesService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.Getter;

@Named @ViewScoped
public class AtualizacaoClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ClientesService service;

    @Inject
    private FacesContext facesContext;

    @Getter
    private DadosParaAtualizacaoCadastralDoCliente dadosParaCadastro;

    @Transactional
    public void atualizarCadastro() {
        try {
            DadosDoCliente dadosDoCliente = service.atualizarCadastroDeCliente(dadosParaCadastro);
            facesContext.addMessage(null, new FacesMessage("Cadastro do cliente " + dadosDoCliente.getNome() + " atualizado com sucesso"));
        } catch (ValidationException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados Incorretos", e.getMessage()));
        }

    }

    public void carregarClientePeloId(int id) {

        service.buscarPorId(id).ifPresentOrElse(cliente ->
            dadosParaCadastro = new DadosParaAtualizacaoCadastralDoCliente(cliente), 
            () ->
                facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/administracao/clientes/listagem"));
    }

}
