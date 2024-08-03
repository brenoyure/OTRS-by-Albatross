package br.albatross.otrs.domain.services.garantia;

import br.albatross.otrs.domain.models.garantia.apis.email.EmailDeGarantia;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class AssuntoEmailService {

    public String getAssuntoDoEmailBaseadoNoServicoDoChamado(EmailDeGarantia emailGarantia) {

        String numeroDoChamado = emailGarantia.getSolicitacaoGarantia().getChamado().getNumeroDoChamado();

        String tipoDoProblema = emailGarantia.getSolicitacaoGarantia().getDescricaoDoProblema().getProblema().getTipo();

        String fornecedor = emailGarantia.getSolicitacaoGarantia().getDadosDoFornecedor().getNome();

        String cliente = emailGarantia.getSolicitacaoGarantia().getDadosDoCliente().getNome();

        String template = String.format("[Ticket#%s] Problema %s %s - %s", numeroDoChamado, tipoDoProblema, fornecedor, cliente);

        return template;

    }

}
