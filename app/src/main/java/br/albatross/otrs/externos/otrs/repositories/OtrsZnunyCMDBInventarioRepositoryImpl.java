package br.albatross.otrs.externos.otrs.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import br.albatross.otrs.externos.InventarioRepository;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;

/**
 * Contexto de persistência com o sistema de chamados OTRS/Znuny, 
 * ao acessar o CMDB e recuperar um equipamento através do seu identificador.
 * 
 * @author breno.brito
 */
@RequestScoped
public class OtrsZnunyCMDBInventarioRepositoryImpl implements InventarioRepository {

    @Resource(lookup = "java:jboss/datasources/OtrsDS")
    private DataSource dataSource;
    private static final byte RESULT_SET_FETCH_SIZE = 1;

	/**
	 * Busca o número de série pelo Nome do Item de Configuração no inventário CMDB do Otrs/Znuny.
	 * 
	 * @param configItemName
	 * @return optional contendo ou não o número de série.
	 */
	public Optional<String> findSerialNumberByIdentifier(String configItemName) {

		try (Connection connection = dataSource.getConnection()) {

		    try (PreparedStatement preparedStatement = connection.prepareStatement(
"""
SELECT 
    x.xml_content_value
FROM
    configitem_version civ
        INNER JOIN
    xml_storage x ON civ.id = x.xml_key
WHERE
            (x.xml_content_key = '[1]{''Version''}[1]{''NumeroDeSerie''}[1]{''Content''}'
            OR
            x.xml_content_key = '[1]{''Version''}[1]{''SerialNumber_Hardware''}[1]{''Content''}')

        AND

            NOT x.xml_type = 'ITSM::ConfigItem::Archiv::22'

        AND
            civ.name = ? 

"""
		            )) {

		        preparedStatement.setFetchSize(RESULT_SET_FETCH_SIZE);
		        preparedStatement.setString(1, configItemName);

		        try (ResultSet resultSet = preparedStatement.executeQuery()) {

		            if (!resultSet.next()) {
		                return Optional.empty();
		            }

		            return Optional.of(resultSet.getString(1));

		        }

		    }

		} catch (SQLException e) { throw new RuntimeException(e); }

	}

}
