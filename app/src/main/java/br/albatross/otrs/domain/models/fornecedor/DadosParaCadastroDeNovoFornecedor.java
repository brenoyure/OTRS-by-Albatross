package br.albatross.otrs.domain.models.fornecedor;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Representam os dados básicos necessários para cadastrar um novo fornecedor no Sistema de Garantia.
 * 
 * @author breno.brito
 */
@Getter @Setter
public class DadosParaCadastroDeNovoFornecedor {

    @NotBlank
    private String nome;

    @NotBlank
    private String emails;

    @NotEmpty
    private Set<Integer> idsDosServicosDoFornecedorNoSistemaDeChamados = new HashSet<>();

}
