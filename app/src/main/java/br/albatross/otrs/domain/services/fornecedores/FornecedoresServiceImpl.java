package br.albatross.otrs.domain.services.fornecedores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.albatross.otrs.domain.models.fornecedor.DadosDoFornecedorDto;
import br.albatross.otrs.domain.models.fornecedor.DadosParaAtualizacaoDeFornecedor;
import br.albatross.otrs.domain.models.fornecedor.DadosParaCadastroDeNovoFornecedor;
import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.persistence.entities.fornecedor.Fornecedor;
import br.albatross.otrs.persistence.repositories.fornecedor.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@ApplicationScoped
public class FornecedoresServiceImpl implements FornecedoresService {

    @Inject
    private FornecedorRepository repository;

    @Override
    public DadosDoFornecedor cadastrarNovoFornecedor(@Valid DadosParaCadastroDeNovoFornecedor novosDados) {

        if (repository.existsByNome(novosDados.getNome())) {
            throw new ValidationException("Já existe outro Fornecedor cadastrado com o nome informado");
        }

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(novosDados.getNome());
        fornecedor.setEmails(novosDados.getEmails());
        fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados().addAll(novosDados.getIdsDosServicosDoFornecedorNoSistemaDeChamados());

        fornecedor = repository.persist(fornecedor);

        return new DadosDoFornecedorDto(fornecedor);

    }

    @Override
    public List<DadosDoFornecedor> listarFornecedoresDisponiveis() {
        return repository.findAll().stream().map(DadosDoFornecedorDto::new).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void excluirFornecedorPeloId(int id) {

        repository.deleteById(id);

    }

    @Override
    public DadosDoFornecedor atualizarFornecedor(@Valid DadosParaAtualizacaoDeFornecedor dados) {

        if (repository.existsByNomeAndNotById(dados.getNome(), dados.getId())) {
            throw new ValidationException("Já existe outro Fornecedor cadastrado com o nome informado");
        }

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(dados.getId());
        fornecedor.setNome(dados.getNome());
        fornecedor.setEmails(dados.getEmails());
        fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados().addAll(dados.getIdsDosServicosDoFornecedorNoSistemaDeChamados());

        fornecedor = repository.merge(fornecedor);

        return new DadosDoFornecedorDto(fornecedor);        

    }

    @Override
    public Optional<DadosDoFornecedor> buscarPorId(int id) {
        return repository.findById(id).map(DadosDoFornecedorDto::new);
    }

    @Override
    public List<Integer> listarOsIdsDosServicosDoFornecedor(int idDoFornecedor) {

        return repository.findIdsDosServicosDoFornecedorNoSistemaDeChamadosById(idDoFornecedor);

    }

}
