package br.albatross.otrs.externos;

import java.util.Optional;

public interface InventarioRepository {

    /**
     * Retorna o número de série de algum equipamento, recebendo como 
     * parâmetro algum identificador que o sistema possua, por exemplo número de patrimônio.
     * 
     * @author breno.brito
     */
	Optional<String> findSerialNumberByIdentifier(String identifier);

}
