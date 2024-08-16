package br.albatross.otrs.externos.otrs.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServico;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServicoDto;
import br.albatross.otrs.externos.ServicosDosChamadosRepository;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;

/**
 * Representa o contexto de persistência com a entidade Service(Serviço do Ticket) do Sistema de Chamados OTRS/Znuny.
 */
@RequestScoped
public class OtrsZnunyTicketsServiceRepository implements ServicosDosChamadosRepository {

    @Resource(lookup = "java:jboss/datasources/OtrsDS")
    private DataSource dataSource;

    @Override
    public List<DadosDoServico> findAll() {

        try (Connection connection = dataSource.getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(
"""
SELECT
    s.id,
    s.name
FROM
    service s
ORDER BY 
    s.name ASC
""")) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    List<DadosDoServico> servicos = new LinkedList<>();
                    while(resultSet.next()) {
                        DadosDoServico servico = 
                                new DadosDoServicoDto(
                                        resultSet.getInt(1), 
                                        resultSet.getString(2));
                        servicos.add(servico);
                    }

                    return servicos;

                }
            }

        } catch (SQLException e) { throw new RuntimeException(e); }

    }

}
