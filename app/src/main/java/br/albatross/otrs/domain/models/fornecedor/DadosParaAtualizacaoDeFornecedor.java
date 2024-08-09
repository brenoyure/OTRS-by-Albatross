package br.albatross.otrs.domain.models.fornecedor;

import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class DadosParaAtualizacaoDeFornecedor extends DadosParaCadastroDeNovoFornecedor {

    @Positive
    private int id;

    public DadosParaAtualizacaoDeFornecedor(DadosDoFornecedor fornecedor) {
        this.id = fornecedor.getId();
        this.setNome(fornecedor.getNome());
        this.setEmails(fornecedor.getEmails());
        this.setIdsDosServicosDoFornecedorNoSistemaDeChamados(fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados());
    }

}
