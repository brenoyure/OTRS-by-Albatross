package br.albatross.otrs.domain.services.cliente;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.Cliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosAtualizacaoCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosCadastroCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosDoClienteDto;
import br.albatross.otrs.domain.services.validacoes.clientes.ValidacaoAtualizacaoCliente;
import br.albatross.otrs.domain.services.validacoes.clientes.ValidacaoCadastroNovoCliente;
import br.albatross.otrs.repositories.cliente.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;

@ApplicationScoped
public class ClientesServiceImpl implements ClientesService {

    @Inject
    private ClienteRepository clienteRepository;

    @Inject
    private List<ValidacaoCadastroNovoCliente> validacoesNovoCliente;

    @Inject
    private List<ValidacaoAtualizacaoCliente> validacoesAtualizacaoCliente;

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
