package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.DadosDoFornecedorDto;
import br.albatross.otrs.domain.services.apis.fornecedores.FornecedoresService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @ViewScoped
public class ListaFornecedoresBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private List<DadosDoFornecedor> fornecedores;

    @Inject
    private FornecedoresService fornecedoresService;    

    @Inject
    private FacesContext facesContext;

    @PostConstruct
    void init() {
        fornecedores = fornecedoresService.listarFornecedoresDisponiveis();
    }

    @Transactional
    public String excluirFornecedor(DadosDoFornecedorDto fornecedor) {

        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        fornecedoresService.excluirFornecedorPeloId(fornecedor.getId());
        facesContext.addMessage(null, new FacesMessage("Fornecedor excluído com sucesso"));
        return facesContext.getViewRoot().getViewId() + "?faces-redirect=true";

    }

}
