package br.albatross.otrs.externos.otrs.repositories;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import br.albatross.otrs.domain.models.garantia.entidades.relatorios.RelatorioServicoDeGarantia;
import br.albatross.otrs.domain.services.relatorios.ServicoDeRelatorioDeServicosDeGarantia;
import br.albatross.otrs.domain.services.relatorios.ServicoDeRelatorioException;
import br.albatross.otrs.persistence.entities.fornecedor.Fornecedor;
import br.albatross.otrs.persistence.repositories.fornecedor.FornecedorRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;

/**
 * 
 * Responsável por retornar os dados de relatório das solicitações de garantia,
 * a partir do sistema de chamados OTRS/Znuny
 * 
 * @author breno.brito
 */
@RequestScoped
public class OtrsZnunyServicoDeRelatorioDeServicosDeGarantia implements ServicoDeRelatorioDeServicosDeGarantia {

    @PersistenceContext(unitName = "otrsdb")
    private EntityManager entityManager;

    @Inject
    private FornecedorRepository fornecedorRepository;    

    @SuppressWarnings("unchecked")
    public List<RelatorioServicoDeGarantia> getRelatorioDosServicosDoFornecedor(int fornecedorId) {

        Fornecedor fornecedor = 
                fornecedorRepository.findById(fornecedorId).orElseThrow(() -> new ServicoDeRelatorioException("Fornecedor com o Id informado não encontrado"));

        Set<Integer> idsDosServicos = 
                fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados();

        List<Tuple> relatorioTuple = 
                entityManager.createNativeQuery(
"""
SELECT 
    COUNT(*)           AS 'total',
    s.id               AS 'id_servico',
    CASE
        WHEN LOCATE('::', s.name) > 0  THEN REVERSE(LEFT(REVERSE(s.name), (INSTR(REVERSE(s.name), '::') - 1)))
        WHEN LOCATE('::', s.name) = 0  THEN s.name
    END AS 'servico',
    MAX(t.create_time) AS 'data_e_hora_ultima_solicitacao'
FROM
    ticket t

INNER JOIN 
    service s ON s.id=t.service_id

WHERE 
    s.id IN (?)

GROUP BY 
    s.id, 'servico'
""", Tuple.class)
        .setParameter(1, idsDosServicos)
        .getResultList();

        List<RelatorioServicoDeGarantia> relatorios = new LinkedList<>();

        relatorioTuple.forEach(tuple -> {

            long total                               =  tuple.get("total",                          Long.class);
            int idDoServico                          =  tuple.get("id_servico",                     Integer.class);
            String nomeDoServico                     =  tuple.get("servico",                        String.class);
            String nomeDoFornecedor                  =  fornecedor.getNome();
            LocalDateTime dataEHoraUltimaSolicitacao =  tuple.get("data_e_hora_ultima_solicitacao", Timestamp.class).toLocalDateTime();

            relatorios.add(new RelatorioServicoDeGarantia(total, idDoServico, nomeDoServico, nomeDoFornecedor, dataEHoraUltimaSolicitacao));

        });

        return relatorios;

    }
}
