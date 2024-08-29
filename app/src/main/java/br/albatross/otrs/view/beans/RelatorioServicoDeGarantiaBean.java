package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import br.albatross.otrs.domain.models.garantia.entidades.relatorios.RelatorioServicoDeGarantia;
import br.albatross.otrs.domain.services.relatorios.ServicoDeRelatorioDeServicosDeGarantia;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class RelatorioServicoDeGarantiaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ServicoDeRelatorioDeServicosDeGarantia servicoDeRelatorio;

    @Getter
    private List<RelatorioServicoDeGarantia> relatorioGeral;

    @Getter
    private List<RelatorioServicoDeGarantia> relatorioMensal;

    @Getter
    private List<RelatorioServicoDeGarantia> relatorioAnual;

    @Getter
    private long totalGeral;

    @Getter
    private long totalMensal;

    @Getter
    private long totalAnual;

    @Getter @Setter
    private int fornecedorId;

    public void obterRelatorioDoFornecedor() {

        relatorioGeral = servicoDeRelatorio
                            .getRelatorioDosServicosDoFornecedor(fornecedorId);

        relatorioGeral.sort((relatorio1, relatorio2) -> relatorio2.getDataEHoraDaUltimaSolicitacao().compareTo(relatorio1.getDataEHoraDaUltimaSolicitacao()));

        relatorioAnual = relatorioGeral
                                .stream()
                                .filter(relatorio -> 
                                            relatorio.getDataEHoraDaUltimaSolicitacao().getYear() == LocalDateTime.now().getYear())
                                .toList();

        relatorioMensal = relatorioAnual
                                    .stream()
                                    .filter(relatorio -> 
                                                relatorio.getDataEHoraDaUltimaSolicitacao().getMonth() == LocalDateTime.now().getMonth())
                                    .toList();

        totalGeral  = relatorioGeral.stream().mapToLong(RelatorioServicoDeGarantia::getTotal).sum();
        totalAnual  = relatorioAnual.stream().mapToLong(RelatorioServicoDeGarantia::getTotal).sum();
        totalMensal = relatorioMensal.stream().mapToLong(RelatorioServicoDeGarantia::getTotal).sum();

    }

}
