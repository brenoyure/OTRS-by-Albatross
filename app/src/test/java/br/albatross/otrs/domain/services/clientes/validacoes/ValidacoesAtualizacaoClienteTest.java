package br.albatross.otrs.domain.services.clientes.validacoes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.albatross.otrs.domain.models.cliente.DadosAtualizacaoCliente;
import br.albatross.otrs.domain.services.clientes.ClientesServiceImpl;
import br.albatross.otrs.persistence.entities.cliente.Cliente;
import br.albatross.otrs.persistence.repositories.cliente.ClienteRepository;
import jakarta.enterprise.inject.Instance;

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

    @Mock
    private Instance<ValidacaoAtualizacaoCliente> validacoes;

    @Mock
    private DadosAtualizacaoCliente dto;

    @Captor
    private ArgumentCaptor<Cliente> clienteCaptor;

    @InjectMocks
    private ClientesServiceImpl service;

    @Test
    @DisplayName("Verifica se os validadores de cadastro de novo cliente são chamadas")
    void deveChamarOsValidadoresAoCadastrarNovoCliente() {

        given(repository.merge(clienteCaptor.capture())).willReturn(retornoDoRepositoryMerge);

        service.atualizarCadastroDeCliente(dto);

        verify(validacoes).forEach(any());

    }

}
