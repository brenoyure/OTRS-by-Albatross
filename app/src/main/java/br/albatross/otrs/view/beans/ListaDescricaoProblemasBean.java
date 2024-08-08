package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema;
import br.albatross.otrs.repositories.problema.DescricaoProblemaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @ViewScoped
public class ListaDescricaoProblemasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private List<DescricaoProblema> problemasDisponiveis;

    @Inject
    private DescricaoProblemaRepository descricaoProblemaDao;

    @PostConstruct
    void init() {
        problemasDisponiveis = descricaoProblemaDao.findAll();
    }

}
