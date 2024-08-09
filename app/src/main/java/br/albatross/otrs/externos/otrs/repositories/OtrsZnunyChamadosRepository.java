package br.albatross.otrs.externos.otrs.repositories;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamadoDto;
import br.albatross.otrs.externos.ChamadoRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Representa o contexto de persistência com o Sistema de Chamados OTRS/Znuny,
 * para operações de leitura com a entidade de Chamados
 * 
 * @author breno.brito
 * 
 */
@RequestScoped
public class OtrsZnunyChamadosRepository implements ChamadoRepository {

    @PersistenceContext(unitName = "otrsdb")
    private EntityManager entityManager;

    private static final byte TRASH_QUEUE_ID = 3;
    private static final byte TICKET_STATE_AS_NEW  = 1;
    private static final byte TICKET_STATE_AS_OPEN = 2;    

    @Override
    public List<DadosDoChamado> findByService(List<Integer> servicesIds) {

        @SuppressWarnings("unchecked")
        List<DadosDoChamado> chamados = 
                    (List<DadosDoChamado>) entityManager
                                                    .createNativeQuery(
"""
SELECT 
    t.id,
    t.tn,
    t.title,
    t.service_id,
    REVERSE(LEFT(REVERSE(s.name), (INSTR(REVERSE(s.name), '::') - 1))),
    t.customer_user_id
FROM
    ticket t
        INNER JOIN
    service s ON t.service_id = s.id
        INNER JOIN
    ticket_state ts ON t.ticket_state_id = ts.id

WHERE
        t.queue_id != ?1
    AND
        (ts.type_id = ?2 
        OR ts.type_id = ?3)
    AND 
        t.service_id IN (?4)

ORDER BY
        t.create_time DESC

""", DadosDoChamadoDto.class)
                                                                    .setParameter(1, TRASH_QUEUE_ID)
                                                                    .setParameter(2, TICKET_STATE_AS_NEW)
                                                                    .setParameter(3, TICKET_STATE_AS_OPEN)
                                                                    .setParameter(4, servicesIds)
                                                                    .getResultList();

        return chamados;

    }

}
