package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosDoClienteDto;
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
public class ListaClientesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private List<DadosDoCliente> clientes;

    @Inject
    private ClientesService clientesService;    

    @Inject
    private FacesContext facesContext;

    @PostConstruct
    void init() {
        clientes = clientesService.listarClientesDisponiveis();
    }

    @Transactional
    public String excluirCliente(DadosDoClienteDto cliente) {

        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        clientesService.excluirClientePeloId((cliente.getId()));
        facesContext.addMessage(null, new FacesMessage("Cliente " + cliente.getNome() + " excluído com sucesso"));
        return facesContext.getViewRoot().getViewId() + "?faces-redirect=true";

    }

}
