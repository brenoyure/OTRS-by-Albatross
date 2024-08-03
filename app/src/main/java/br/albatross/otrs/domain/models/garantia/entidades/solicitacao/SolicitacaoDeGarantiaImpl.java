package br.albatross.otrs.domain.models.garantia.entidades.solicitacao;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.models.garantia.apis.problemas.DescricaoProblema;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SolicitacaoDeGarantiaImpl implements SolicitacaoDeGarantia {

    private static final long serialVersionUID = 1L;

    private String numeroDeSerie;

    private DescricaoProblema descricaoDoProblema;

    private EmailDeGarantia emailDeGarantia;

    private DadosDoChamado chamado;

    private DadosDoFornecedor dadosDoFornecedor;

    private DadosDoCliente dadosDoCliente;

    public void setDadosDoFornecedor(DadosDoFornecedor dadosDoFornecedor) {

        this.dadosDoFornecedor = dadosDoFornecedor;
        emailDeGarantia.getDadosDoEnvio().setDestinatario(dadosDoFornecedor.getEmails());

    }

}
