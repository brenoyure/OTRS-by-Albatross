package br.albatross.otrs.domain.services.fornecedores;

import java.util.List;
import java.util.Optional;

import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.DadosDoFornecedorDto;
import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.DadosParaAtualizacaoDeFornecedor;
import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.DadosParaCadastroDeNovoFornecedor;
import jakarta.validation.Valid;

public interface FornecedoresService {

    DadosDoFornecedorDto cadastrarNovoFornecedor(@Valid DadosParaCadastroDeNovoFornecedor dados);

    List<DadosDoFornecedor> listarFornecedoresDisponiveis();

    void excluirFornecedorPeloId(int id);

    DadosDoFornecedor atualizarFornecedor(@Valid DadosParaAtualizacaoDeFornecedor dados);

    Optional<DadosDoFornecedor> buscarPorId(int id);

    List<Integer> listarOsIdsDosServicosDoFornecedor(int idDoFornecedor);
}
