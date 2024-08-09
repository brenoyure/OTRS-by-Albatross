package br.albatross.otrs.view.beans;

import br.albatross.otrs.domain.models.fornecedor.DadosParaCadastroDeNovoFornecedor;
import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.services.fornecedores.FornecedoresService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
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
        facesContext.getExternalContext().getFlash().setKeepMessages(true);
    }

    @Transactional
    public String cadastrarFornecedor() {
        try {

            DadosDoFornecedor dadosDoFornecedor = service.cadastrarNovoFornecedor(dadosParaCadastro);

            facesContext.addMessage(null, new FacesMessage("Fornecedor " + dadosDoFornecedor.getNome() + " cadastrado com sucesso"));
            return facesContext.getViewRoot().getViewId() + "?faces-redirect=true";

        } catch (ValidationException e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.getMessage(), null));
            return null;
        }

    }

}
