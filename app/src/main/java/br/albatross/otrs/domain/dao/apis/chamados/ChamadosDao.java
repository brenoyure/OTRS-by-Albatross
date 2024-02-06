package br.albatross.otrs.domain.dao.apis.chamados;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;

public interface ChamadosDao {

	List<DadosDoChamado> findAllOpened();

}
