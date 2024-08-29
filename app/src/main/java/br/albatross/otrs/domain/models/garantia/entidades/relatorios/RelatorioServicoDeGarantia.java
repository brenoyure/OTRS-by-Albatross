package br.albatross.otrs.domain.models.garantia.entidades.relatorios;

import java.time.LocalDateTime;
import java.util.Objects;

public class RelatorioServicoDeGarantia {

    private final long total;
    private final int idDoServico;
    private final String nomeDoServico;
    private final String nomeDoFornecedor;
    private final LocalDateTime dataEHoraDaUltimaSolicitacao;

    public RelatorioServicoDeGarantia(long total, int idDoServico, String nomeDoServico, String nomeDoFornecedor, LocalDateTime dataEHoraDaUltimaSolicitacao) {
        this.total = total;
        this.idDoServico = idDoServico;
        this.nomeDoServico = nomeDoServico;
        this.nomeDoFornecedor = nomeDoFornecedor;
        this.dataEHoraDaUltimaSolicitacao = dataEHoraDaUltimaSolicitacao;
    }

    public long getTotal() {
        return total;
    }

    public int getIdDoServico() {
        return idDoServico;
    }

    public String getNomeDoServico() {
        return nomeDoServico;
    }

    public String getNomeDoFornecedor() {
        return nomeDoFornecedor;
    }

    public LocalDateTime getDataEHoraDaUltimaSolicitacao() {
        return dataEHoraDaUltimaSolicitacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDoServico, nomeDoFornecedor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RelatorioServicoDeGarantia other = (RelatorioServicoDeGarantia) obj;
        return idDoServico == other.idDoServico && Objects.equals(nomeDoFornecedor, other.nomeDoFornecedor);
    }

}
