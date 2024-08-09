package br.albatross.otrs.domain.services.garantia;

import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import jakarta.validation.Valid;

public interface SolicitacaoDeGarantiaService {

    void solicitarGarantia(@Valid SolicitacaoDeGarantia solicitacao);

}
