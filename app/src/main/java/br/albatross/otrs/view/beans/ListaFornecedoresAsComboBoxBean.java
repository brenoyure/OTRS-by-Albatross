package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.models.fornecedor.FornecedorComboBox;
import br.albatross.otrs.persistence.repositories.fornecedor.FornecedorRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @ViewScoped
public class ListaFornecedoresAsComboBoxBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private FornecedorRepository repository;

    @Getter
    private List<FornecedorComboBox> fornecedores;

    @PostConstruct
    void init() {
        fornecedores = repository.findAllAsFornecedorComboBoxOrderByNome();
    }

}
