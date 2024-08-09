package br.albatross.otrs.domain.services.clientes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.then;

import java.time.LocalTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.albatross.otrs.domain.models.cliente.DadosAtualizacaoCliente;
import br.albatross.otrs.domain.models.cliente.DadosCadastroCliente;
import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.services.clientes.validacoes.ValidacaoAtualizacaoCliente;
import br.albatross.otrs.domain.services.clientes.validacoes.ValidacaoCadastroNovoCliente;
import br.albatross.otrs.persistence.entities.cliente.Cliente;
import br.albatross.otrs.persistence.repositories.cliente.ClienteRepository;
import jakarta.enterprise.inject.Instance;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testa a Conversão entre DTO e entidade Cliente ao Cadastrar e Atualizar")
class ClientesServiceImplDtoToClientTestTest {

    @Mock
    private ClienteRepository repository;

    @Captor
    private ArgumentCaptor<Cliente> clienteCaptor;

    @Captor
    private ArgumentCaptor<Cliente> clienteMergeCaptor;

    @Mock
    private Cliente cliente;

    @Mock
    private Instance<ValidacaoCadastroNovoCliente> validacoesNovoCliente;

    @Mock
    private Instance<ValidacaoAtualizacaoCliente> validacoesAtualizacaoCliente;

    @InjectMocks
    private ClientesServiceImpl service;

    @Test
    @DisplayName("Verifica se a entidade Cliente é corretamente preenchida com os dados obrigatórios do DTO de Cadastro")
    void verificaSeOsDadosDaEntidadeForamPreenchidosAntesDoRepositoryPersist() {

        DadosCadastroCliente dto = new DadosCadastroCliente();
        dto.setNome("Empresa XPTO");
        dto.setDescricao("Descrição da Empresa XPTO");
        dto.setPossuiHorarioDeAlmoco(false);
        dto.setHorarioInicioDoExpediente(LocalTime.of(8, 0));
        dto.setHorarioFimDoExpediente(LocalTime.of(17, 0));        

        DadosDoCliente dadosDoCliente = service.cadastrarNovoCliente(dto);

        then(repository).should().persist(clienteCaptor.capture());

        Cliente cliente = clienteCaptor.getValue();

        assertEquals(dto.getNome(), cliente.getNome());
        assertEquals(dto.getDescricao(), cliente.getDescricao());
        assertEquals(dto.getHorarioInicioDoExpediente(), cliente.getHorarioInicioDoExpediente());
        assertEquals(dto.getHorarioFimDoExpediente(), cliente.getHorarioFimDoExpediente());
        assertEquals(dto.getPossuiHorarioDeAlmoco().booleanValue(), cliente.isPossuiHorarioDeAlmoco());

        assertEquals(cliente.getId(), dadosDoCliente.getId());
        assertEquals(cliente.getNome(), dadosDoCliente.getNome());
        assertEquals(cliente.getDescricao(), dadosDoCliente.getDescricao());
        assertEquals(cliente.getHorarioInicioDoExpediente(), dadosDoCliente.getHorarios().getHorarioInicioDoExpediente());
        assertEquals(cliente.getHorarioFimDoExpediente(), dadosDoCliente.getHorarios().getHorarioFimDoExpediente());
        assertEquals(cliente.isPossuiHorarioDeAlmoco(), dadosDoCliente.getHorarios().possuiHorarioDeAlmoco());

    }

    @Test
    @DisplayName("Verifica se a entidade Cliente é corretamente preenchida com os dados obrigatórios do DTO de Atualização")
    void testaSeOClienteDoMergePossuiOsMesmosAtributosDoDto() {

        DadosAtualizacaoCliente dto = new DadosAtualizacaoCliente();
        dto.setId(1);
        dto.setNome("Empresa XPTO");
        dto.setDescricao("Descrição da Empresa XPTO");
        dto.setPossuiHorarioDeAlmoco(false);
        dto.setHorarioInicioDoExpediente(LocalTime.of(8, 0));
        dto.setHorarioFimDoExpediente(LocalTime.of(17, 0));

        /*
         * Instância do Cliente retornado do método merge()
         */
        Cliente clienteMerge = new Cliente();
        clienteMerge.setId(1);
        clienteMerge.setNome("Empresa XPTO");
        clienteMerge.setDescricao("Descrição da Empresa XPTO");
        clienteMerge.setPossuiHorarioDeAlmoco(false);
        clienteMerge.setHorarioInicioDoExpediente(LocalTime.of(8, 0));
        clienteMerge.setHorarioFimDoExpediente(LocalTime.of(17, 0));

        BDDMockito
            .given(repository.merge(clienteCaptor.capture()))
            .willReturn(clienteMerge);

        service.atualizarCadastroDeCliente(dto);

        assertEquals(dto.getNome(), clienteMerge.getNome());
        assertEquals(dto.getDescricao(), clienteMerge.getDescricao());
        assertEquals(dto.getHorarioInicioDoExpediente(), clienteMerge.getHorarioInicioDoExpediente());
        assertEquals(dto.getHorarioFimDoExpediente(), clienteMerge.getHorarioFimDoExpediente());
        assertEquals(dto.getPossuiHorarioDeAlmoco(), clienteMerge.isPossuiHorarioDeAlmoco());

        assertNull(clienteMerge.getInicioDoHorarioDeAlmoco());
        assertNull(clienteMerge.getFimDoHorarioDeAlmoco());

        BDDMockito
            .verify(repository)
            .merge(clienteCaptor.getValue());

    }

}
