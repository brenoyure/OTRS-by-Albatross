package br.albatross.otrs.domain.services.apis.chamados;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;

public interface ServicoDeChamados {

	List<DadosDoChamado> listarTodosOsChamadosAbertos();

}
