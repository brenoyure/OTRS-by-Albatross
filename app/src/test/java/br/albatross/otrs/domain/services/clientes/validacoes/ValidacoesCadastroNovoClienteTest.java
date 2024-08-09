package br.albatross.otrs.domain.services.clientes.validacoes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.albatross.otrs.domain.models.cliente.DadosCadastroCliente;
import br.albatross.otrs.domain.services.clientes.ClientesServiceImpl;
import br.albatross.otrs.persistence.repositories.cliente.ClienteRepository;
import jakarta.enterprise.inject.Instance;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testa se as validações do tipo ValidacaoCadastroNovoCliente são executadas ao cadastrar um novo Cliente")
class ValidacoesCadastroNovoClienteTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private DadosCadastroCliente dto;

    @Mock
    private Instance<ValidacaoCadastroNovoCliente> validacoesNovoCadastro;

    @Captor
    private ArgumentCaptor<ValidacaoCadastroNovoCliente> validacaoCaptor;
    
    @InjectMocks
    private ClientesServiceImpl service;

    @Test
    @DisplayName("Verifica se os validadores de cadastro de novo cliente são chamadas")
    void deveChamarOsValidadoresAoCadastrarNovoCliente() {

        service.cadastrarNovoCliente(dto);
        verify(validacoesNovoCadastro).forEach(any());

    }

}




















