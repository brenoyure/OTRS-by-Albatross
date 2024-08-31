package br.albatross.otrs.domain.services.garantia;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class AssuntoEmailDeGarantiaService {

    public String getAssuntoDoEmailBaseadoNoServicoDoChamado(SolicitacaoDeGarantia solicitacaoDeGarantia) {

        String numeroDoChamado = solicitacaoDeGarantia.getChamado().getNumeroDoChamado();

        String tipoDoProblema = solicitacaoDeGarantia.getDescricaoDoProblema().getProblema().getTipo();

        String fornecedor = solicitacaoDeGarantia.getDadosDoFornecedor().getNome();

        String cliente = solicitacaoDeGarantia.getDadosDoCliente().getNome();

        String template = String.format("[Ticket#%s] Problema %s %s - %s", numeroDoChamado, tipoDoProblema, fornecedor, cliente);

        return template;

    }

}
