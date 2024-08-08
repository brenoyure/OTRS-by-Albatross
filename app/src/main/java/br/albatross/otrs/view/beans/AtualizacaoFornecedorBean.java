package br.albatross.otrs.view.beans;

import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.DadosParaAtualizacaoDeFornecedor;
import br.albatross.otrs.domain.services.fornecedores.FornecedoresService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.Getter;

@Named @ViewScoped
public class AtualizacaoFornecedorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FornecedoresService service;

    @Inject
    private FacesContext facesContext;

    @Getter
    private DadosParaAtualizacaoDeFornecedor dadosParaCadastro;

    @Transactional
    public void atualizarCadastro() {

        DadosDoFornecedor dadosDoFornecedor = service.atualizarFornecedor(dadosParaCadastro);
        facesContext.addMessage(null, new FacesMessage("Cadastro do Fornecedor " + dadosDoFornecedor.getNome() + " atualizado com sucesso"));

    }

    public void carregarFornecedorPeloId(int id) {

        service.buscarPorId(id).ifPresentOrElse(fornecedor ->
            dadosParaCadastro = new DadosParaAtualizacaoDeFornecedor(fornecedor), 
            () ->
                facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/administracao/fornecedores/listagem"));
    }

}
