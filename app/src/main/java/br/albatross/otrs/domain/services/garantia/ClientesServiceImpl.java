package br.albatross.otrs.domain.services.garantia;

import java.util.List;
import java.util.stream.Collectors;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.Cliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosDoClienteDto;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosParaAtualizacaoCadastralDoCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosParaCadastroDeNovoCliente;
import br.albatross.otrs.domain.services.apis.clientes.ClientesService;
import br.albatross.otrs.repositories.api.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@ApplicationScoped
public class ClientesServiceImpl implements ClientesService {

    @Inject
    private ClienteRepository clienteRepository;
    
    @Override
    public DadosDoCliente cadastrarNovoCliente(@Valid DadosParaCadastroDeNovoCliente dadosDoNovoCliente) {
        
        if (clienteRepository.existsByNome(dadosDoNovoCliente.getNome())) {
            throw new ValidationException("Já existe um cliente cadastrado com o nome informado");
        }

        if (clienteRepository.existsByDescricao(dadosDoNovoCliente.getDescricao())) {
            throw new ValidationException("Já existe um cliente cadastrado com a descrição informada");
        }

        if (dadosDoNovoCliente.getPossuiHorarioDeAlmoco() && (dadosDoNovoCliente.getInicioDoHorarioDeAlmoco() == null || dadosDoNovoCliente.getFimDoHorarioDeAlmoco() == null)) {
            throw new ValidationException("Foi informado que o cliente " + dadosDoNovoCliente.getNome() + " possui horário de almoço, porém o(s) horário(s) de início ou fim não foram informados");
        }

        Cliente novoCliente = new Cliente(dadosDoNovoCliente);
        novoCliente = clienteRepository.persist(novoCliente);

        return new DadosDoClienteDto(novoCliente);

    }

    @Override
    public DadosDoCliente atualizarCadastroDeCliente(@Valid DadosParaAtualizacaoCadastralDoCliente dadosAtualizados) {

        if (clienteRepository.existsByNomeAndNotById(dadosAtualizados.getNome(), dadosAtualizados.getId())) {
            throw new ValidationException("Já existe outro cliente cadastrado com o nome informado");
        }

        if (clienteRepository.existsByDescricaoAndNotById(dadosAtualizados.getDescricao(), dadosAtualizados.getId())) {
            throw new ValidationException("Já existe outro cliente cadastrado com a descrição informada");
        }

        if (dadosAtualizados.getPossuiHorarioDeAlmoco() && (dadosAtualizados.getInicioDoHorarioDeAlmoco() == null || dadosAtualizados.getFimDoHorarioDeAlmoco() == null)) {
            throw new ValidationException("Foi informado que o cliente " + dadosAtualizados.getNome() + " possui horário de almoço, porém o(s) horário(s) de início ou fim não foram informados");
        }

        Cliente clienteAtualizado = new Cliente(dadosAtualizados);
        clienteAtualizado.setId(dadosAtualizados.getId());

        clienteAtualizado = clienteRepository.merge(clienteAtualizado);

        return new DadosDoClienteDto(clienteAtualizado);        

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
    public void excluirCliente(int id) {

        if (clienteRepository.existsById(id)) {
            Cliente referenceDoCliente = clienteRepository.getReferenceById(id);
            clienteRepository.remove(referenceDoCliente);
        }

    }

}
