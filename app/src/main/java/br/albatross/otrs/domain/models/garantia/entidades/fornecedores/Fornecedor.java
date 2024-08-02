package br.albatross.otrs.domain.models.garantia.entidades.fornecedores;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Cacheable;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name = "fornecedores")
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Cacheable
public class Fornecedor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true)
    private String emails;

    @ElementCollection(targetClass = Integer.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "servicos_do_fornecedor", joinColumns = @JoinColumn(name = "fk_fornecedor_id"))
    @Column(name = "id_do_servico_no_sistema_de_chamados", nullable = false)
    private Set<Integer> idsDosServicosDoFornecedorNoSistemaDeChamados = new HashSet<>();

}
