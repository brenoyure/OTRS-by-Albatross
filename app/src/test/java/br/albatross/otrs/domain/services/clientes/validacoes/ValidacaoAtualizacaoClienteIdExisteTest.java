package br.albatross.otrs.domain.services.clientes.validacoes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.albatross.otrs.domain.models.cliente.DadosAtualizacaoCliente;
import br.albatross.otrs.persistence.repositories.cliente.ClienteRepository;
import jakarta.validation.ValidationException;

@DisplayName("Validação de Atualização de Cliente se o Id existe")
@ExtendWith(MockitoExtension.class)
class ValidacaoAtualizacaoClienteIdExisteTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private DadosAtualizacaoCliente dados;

    @InjectMocks
    private ValidacaoAtualizacaoClienteIdExiste validador;

    @Test
    @DisplayName("Deve lançar ValidationException caso o Cliente com o Id informado não exista")
    void deveLancarValidationExceptionCasoNaoExistaUmClienteComOIdInformadoParaSerAtualizado() {

        int clienteId = 1;

        BDDMockito
            .given(dados.getId()).willReturn(clienteId);

        BDDMockito
            .given(repository.existsById(dados.getId()))
            .willReturn(false);

        ValidationException validationException = 
                assertThrows(ValidationException.class, () -> validador.validar(dados));

        String expectedMessage = 
                "Cliente com o ID informado não encontrado";

        assertEquals(
                expectedMessage, 
                validationException.getMessage());

    }

}
