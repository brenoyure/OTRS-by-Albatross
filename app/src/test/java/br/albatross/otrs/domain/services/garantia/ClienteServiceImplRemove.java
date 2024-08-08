package br.albatross.otrs.domain.services.garantia;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.albatross.otrs.domain.models.garantia.entidades.cliente.Cliente;
import br.albatross.otrs.domain.services.clientes.ClientesServiceImpl;
import br.albatross.otrs.repositories.cliente.ClienteRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testa o método Remove do ClienteService")
class ClienteServiceImplRemoveTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private Cliente cliente;

    @InjectMocks
    private ClientesServiceImpl service;

    private int mockedClienteId = 1;    

    @Test
    @DisplayName("Deve invocar o remove() logo após o getReference() se o cliente com o Id informado existir")
    void verificaAOrdemDeInvocacaoDosMetodosGetReferenceERemove() {

        BDDMockito
            .given(repository.existsById(mockedClienteId))
            .willReturn(true);

        BDDMockito
            .given(repository.getReferenceById(mockedClienteId))
            .willReturn(cliente);

        service
            .excluirClientePeloId(mockedClienteId);

        InOrder inOrder = 
            Mockito.inOrder(repository);

        inOrder.verify(repository).existsById(mockedClienteId);
        inOrder.verify(repository).getReferenceById(mockedClienteId);
        inOrder.verify(repository).remove(cliente);

    }    

    @Test
    @DisplayName("Não deve invocar os getReference() e remove(), dado que o cliente com o id informado não existe")
    void deveInvocarOGetReferenceEoRemoveApenasSeOClienteExistirPeloId() {

        BDDMockito
            .given(repository.existsById(mockedClienteId))
            .willReturn(false);

        service
            .excluirClientePeloId(mockedClienteId);

        BDDMockito
            .verify(repository, Mockito.never())
            .getReferenceById(mockedClienteId);

        BDDMockito
            .verify(repository, Mockito.never())
            .remove(cliente);        

    }    

}
