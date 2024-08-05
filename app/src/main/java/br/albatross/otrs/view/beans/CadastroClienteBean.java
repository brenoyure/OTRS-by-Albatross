package br.albatross.otrs.view.beans;

import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosParaCadastroDeNovoCliente;
import br.albatross.otrs.domain.services.apis.clientes.ClientesService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @ViewScoped
public class CadastroClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ClientesService service;

    @Inject
    private FacesContext facesContext;

    @Getter
    private DadosParaCadastroDeNovoCliente dadosParaCadastro;

    @PostConstruct
    void init() {
        dadosParaCadastro = new DadosParaCadastroDeNovoCliente();
    }

    @Transactional
    public String cadastrarCliente() {

        DadosDoCliente dadosDoCliente = service.cadastrarNovoCliente(dadosParaCadastro);
        facesContext.addMessage(null, new FacesMessage("Cliente " + dadosDoCliente.getNome() + " cadastrado com sucesso"));
        return facesContext.getViewRoot().getViewId() + "?faces-redirect=true";

    }

}
