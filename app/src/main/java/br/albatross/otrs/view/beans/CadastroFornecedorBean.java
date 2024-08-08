package br.albatross.otrs.view.beans;

import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.DadosParaCadastroDeNovoFornecedor;
import br.albatross.otrs.domain.services.fornecedores.FornecedoresService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @RequestScoped
public class CadastroFornecedorBean {

    @Inject
    private FornecedoresService service;

    @Inject
    private FacesContext facesContext;

    @Getter
    private DadosParaCadastroDeNovoFornecedor dadosParaCadastro;

    @PostConstruct
    void init() {
        dadosParaCadastro = new DadosParaCadastroDeNovoFornecedor();
    }

    @Transactional
    public String cadastrarFornecedor() {

        DadosDoFornecedor dadosDoFornecedor = service.cadastrarNovoFornecedor(dadosParaCadastro);
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
        facesContext.addMessage(null, new FacesMessage("Fornecedor " + dadosDoFornecedor.getNome() + " cadastrado com sucesso"));

        return facesContext.getViewRoot().getViewId() + "?faces-redirect=true";
        
    }
    
}
