package br.albatross.otrs.domain.services.clientes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.albatross.otrs.domain.models.cliente.DadosAtualizacaoCliente;
import br.albatross.otrs.domain.models.cliente.DadosCadastroCliente;
import br.albatross.otrs.domain.models.cliente.DadosDoClienteDto;
import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.services.clientes.validacoes.ValidacaoAtualizacaoCliente;
import br.albatross.otrs.domain.services.clientes.validacoes.ValidacaoCadastroNovoCliente;
import br.albatross.otrs.persistence.entities.cliente.Cliente;
import br.albatross.otrs.persistence.repositories.cliente.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class ClientesServiceImpl implements ClientesService {

    @Inject
    private ClienteRepository clienteRepository;

    @Inject
    private Instance<ValidacaoCadastroNovoCliente> validacoesNovoCliente;

    @Inject
    private Instance<ValidacaoAtualizacaoCliente> validacoesAtualizacaoCliente;

    @Override
    public DadosDoCliente cadastrarNovoCliente(@Valid DadosCadastroCliente dadosDoNovoCliente) {

        validacoesNovoCliente.forEach(validacao -> validacao.validar(dadosDoNovoCliente));

        Cliente novoCliente = new Cliente(dadosDoNovoCliente);
        clienteRepository.persist(novoCliente);

        return new DadosDoClienteDto(novoCliente);

    }

    @Override
    public DadosDoCliente atualizarCadastroDeCliente(@Valid DadosAtualizacaoCliente dadosAtualizados) {

        validacoesAtualizacaoCliente.forEach(validacao -> validacao.validar(dadosAtualizados));

        return new DadosDoClienteDto(clienteRepository.merge(new Cliente(dadosAtualizados)));

    }

    @Override
    public List<DadosDoCliente> listarClientesDisponiveis() {
        return clienteRepository
                .findAll()
                .stream()
                .map(DadosDoClienteDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void excluirClientePeloId(int id) {

        if (clienteRepository.existsById(id)) {
            Cliente referenceDoCliente = clienteRepository.getReferenceById(id);
            clienteRepository.remove(referenceDoCliente);
        }

    }

    @Override
    public Optional<DadosDoCliente> buscarPorId(int id) {
        return clienteRepository.findById(id).map(DadosDoClienteDto::new);
    }

}
