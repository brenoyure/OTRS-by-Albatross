package br.albatross.otrs.domain.services.garantia;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.albatross.otrs.domain.dao.apis.fornecedores.FornecedoresDao;
import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.DadosDoFornecedorDto;
import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.DadosParaAtualizacaoDeFornecedor;
import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.DadosParaCadastroDeNovoFornecedor;
import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.Fornecedor;
import br.albatross.otrs.domain.services.apis.fornecedores.FornecedoresService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@RequestScoped
public class FornecedoresServiceImpl implements FornecedoresService {

    @Inject
    private FornecedoresDao fornecedoresDao;

    @Override
    public DadosDoFornecedorDto cadastrarNovoFornecedor(@Valid DadosParaCadastroDeNovoFornecedor novosDados) {

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(novosDados.getNome());
        fornecedor.setEmails(novosDados.getEmails());
        fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados().addAll(novosDados.getIdsDosServicosDoFornecedorNoSistemaDeChamados());

        fornecedor = fornecedoresDao.persist(fornecedor);

        return new DadosDoFornecedorDto(fornecedor);

    }

    @Override
    public List<DadosDoFornecedor> listarFornecedoresDisponiveis() {
        return fornecedoresDao.findAll().stream().map(DadosDoFornecedorDto::new).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void excluirFornecedorPeloId(int id) {

        if (fornecedoresDao.existsById(id)) {

            Fornecedor fornecedor = fornecedoresDao.getReferenceById(id);
            fornecedoresDao.remove(fornecedor);
        }

    }

    @Override
    public DadosDoFornecedor atualizarFornecedor(@Valid DadosParaAtualizacaoDeFornecedor dados) {

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(dados.getId());
        fornecedor.setNome(dados.getNome());
        fornecedor.setEmails(dados.getEmails());
        fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados().addAll(dados.getIdsDosServicosDoFornecedorNoSistemaDeChamados());

        fornecedor = fornecedoresDao.merge(fornecedor);

        return new DadosDoFornecedorDto(fornecedor);        

    }

    @Override
    public Optional<DadosDoFornecedor> buscarPorId(int id) {
        return fornecedoresDao.findById(id).map(DadosDoFornecedorDto::new);
    }

    @Override
    public List<Integer> listarOsIdsDosServicosDoFornecedor(int idDoFornecedor) {

        return fornecedoresDao.findIdsDosServicosDoFornecedorNoSistemaDeChamadosById(idDoFornecedor);

    }

}
