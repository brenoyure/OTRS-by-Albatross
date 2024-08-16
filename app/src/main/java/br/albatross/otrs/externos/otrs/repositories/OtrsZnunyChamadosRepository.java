package br.albatross.otrs.externos.otrs.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamadoDto;
import br.albatross.otrs.externos.ChamadoRepository;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;

/**
 * Representa o contexto de persistência com o Sistema de Chamados OTRS/Znuny,
 * para operações de leitura com a entidade de Chamados
 * 
 * @author breno.brito
 * 
 */
@RequestScoped
public class OtrsZnunyChamadosRepository implements ChamadoRepository {

    @Resource(lookup = "java:jboss/datasources/OtrsDS")
    private DataSource dataSource;
    private static final byte RESULT_SET_FETCH_SIZE = 5;

    private static final byte TRASH_QUEUE_ID = 3;
    private static final byte TICKET_STATE_AS_NEW  = 1;
    private static final byte TICKET_STATE_AS_OPEN = 2;

    @Override
    public List<DadosDoChamado> findByService(List<Integer> servicesIds) {

        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(getFindByServiceIdSqlQuery(servicesIds.size()))) {

                preparedStatement.setFetchSize(RESULT_SET_FETCH_SIZE);

                preparedStatement.setInt(1, TRASH_QUEUE_ID);
                preparedStatement.setInt(2, TICKET_STATE_AS_NEW);
                preparedStatement.setInt(3, TICKET_STATE_AS_OPEN);

                int serviceIdPreparedStatementCurrentIndex = 4;

                for (Integer serviceId : servicesIds) {
                    preparedStatement.setInt(serviceIdPreparedStatementCurrentIndex, serviceId.intValue());
                    serviceIdPreparedStatementCurrentIndex++;
                }

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    List<DadosDoChamado> chamados = new LinkedList<>();

                    while(resultSet.next()) {

                        DadosDoChamado chamado = 
                                new DadosDoChamadoDto(
                                        resultSet.getLong(1), 
                                        resultSet.getString(2), 
                                        resultSet.getString(3), 
                                        resultSet.getInt(4), 
                                        resultSet.getString(5), 
                                        resultSet.getString(6));

                        chamados.add(chamado);

                    }

                    return chamados;

                }

            }

        } catch (SQLException e) { throw new RuntimeException(e); }

    }

    private String getFindByServiceIdSqlQuery(int serviceQuantity) {
        StringBuilder sb = new StringBuilder();

        sb.append(
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
        t.queue_id != ?
    AND
        (ts.type_id = ? 
        OR ts.type_id = ?)
    AND 
        t.service_id IN (
""");

        for (int i = 1; i <= serviceQuantity; i++) {
            sb.append("?");
            if (i < serviceQuantity) {
                sb.append(", ");
            }
        }

        sb.append(
"""
)

ORDER BY
        t.create_time DESC

""");

        return sb.toString();

    }

}

