package br.albatross.otrs.domain.services.validacoes.clientes;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.albatross.otrs.domain.models.garantia.entidades.cliente.Cliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosAtualizacaoCliente;
import br.albatross.otrs.domain.services.clientes.ClientesServiceImpl;
import br.albatross.otrs.repositories.cliente.ClienteRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testa se as validações do tipo ValidacaoAtualizacaoCliente são executadas ao atualizar um Cliente")
class ValidacoesAtualizacaoClienteTest {

    @Mock
    private ClienteRepository repository;

    /**
     * Representa a instância retornada após invocar o <code>ClienteRepository.merge(cliente)</code>.
     */
    @Mock
    private Cliente retornoDoRepositoryMerge;

    @Spy
    private List<ValidacaoAtualizacaoCliente> validacoesNovoCadastro = new ArrayList<>();

    @Mock
    private ValidacaoAtualizacaoCliente validador1;

    @Mock
    private ValidacaoAtualizacaoCliente validador2;

    @Captor
    private ArgumentCaptor<Cliente> clienteCaptor;

    @InjectMocks
    private ClientesServiceImpl service;

    @Test
    @DisplayName("Verifica se os validadores de cadastro de novo cliente são chamadas")
    void deveChamarOsValidadoresAoCadastrarNovoCliente() {

        DadosAtualizacaoCliente dto = new DadosAtualizacaoCliente();
        dto.setId(1);
        dto.setNome("Empresa XPTO");
        dto.setDescricao("Descrição da Empresa XPTO");
        dto.setPossuiHorarioDeAlmoco(false);
        dto.setHorarioInicioDoExpediente(LocalTime.of(8, 0));
        dto.setHorarioFimDoExpediente(LocalTime.of(17, 0)); 

        validacoesNovoCadastro.add(validador1);
        validacoesNovoCadastro.add(validador2);

        BDDMockito
            .given(repository.merge(clienteCaptor.capture()))
            .willReturn(retornoDoRepositoryMerge);

        service.atualizarCadastroDeCliente(dto);

        BDDMockito
            .then(validador1)
            .should()
            .validar(dto);

        BDDMockito
            .then(validador2)
            .should()
            .validar(dto);

    }

}
