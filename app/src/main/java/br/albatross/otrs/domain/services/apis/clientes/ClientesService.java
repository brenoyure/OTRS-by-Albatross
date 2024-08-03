package br.albatross.otrs.domain.services.apis.clientes;

import java.util.List;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosParaAtualizacaoCadastralDoCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosParaCadastroDeNovoCliente;
import jakarta.validation.Valid;

public interface ClientesService {

    DadosDoCliente cadastrarNovoCliente(@Valid DadosParaCadastroDeNovoCliente dadosDoNovoCliente);

    DadosDoCliente atualizarCadastroDeCliente(@Valid DadosParaAtualizacaoCadastralDoCliente dadosAtualizados);

    List<DadosDoCliente> listarClientesDisponiveis();

    void excluirClientePeloId(int id);

}
