package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.dao.apis.chamados.ServicosDosChamadosDao;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServico;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;

@Named @ViewScoped
public class ListaServicosDoSistemaDeChamadosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ServicosDosChamadosDao dao;

    @Getter
    private List<DadosDoServico> servicosDisponiveis;

    @PostConstruct
    void init() {
        servicosDisponiveis = dao.listarServicosDisponiveis();
    }

}
