package br.albatross.otrs.view.beans;

import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosCadastroCliente;
import br.albatross.otrs.domain.services.cliente.ClientesService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.Getter;

@Named @ViewScoped
public class CadastroClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ClientesService service;

    @Inject
    private FacesContext facesContext;

    @Getter
    private DadosCadastroCliente dadosParaCadastro;

    @PostConstruct
    void init() {
        dadosParaCadastro = new DadosCadastroCliente();
    }

    @Transactional
    public String cadastrarCliente() {
        try {

            facesContext.getExternalContext().getFlash().setKeepMessages(true);

            DadosDoCliente dadosDoCliente = service.cadastrarNovoCliente(dadosParaCadastro);
            facesContext.addMessage(null, new FacesMessage("Cliente " + dadosDoCliente.getNome() + " cadastrado com sucesso"));

            return facesContext.getViewRoot().getViewId() + "?faces-redirect=true";

        } catch (ValidationException e) {

            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Dados Incorretos", e.getMessage()));
            return null;

        }

    }

}
