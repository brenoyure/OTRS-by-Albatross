package br.albatross.otrs.persistence.repositories.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.jpa.AvailableHints;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.albatross.otrs.persistence.entities.cliente.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@ExtendWith(MockitoExtension.class)
class ClienteRepositoryImplTest {

    private static final String CLIENTE_EXISTS_BY_ID_QUERY = "SELECT EXISTS(SELECT c FROM Cliente c WHERE c.id = ?1)";

    @Mock
    EntityManager entityManager;
    
    @Mock
    TypedQuery<Cliente> clienteQuery;

    @Mock
    TypedQuery<Boolean> booleanQuery;

    @InjectMocks
    ClienteRepositoryImpl repository;

    @Test
    void deveRetornarOsClientesOrdenadoPorNome() {

        List<Cliente> expectedClientesList = new ArrayList<>();

        Cliente c1 = new Cliente();
        c1.setId(2);
        c1.setNome("Amanda Lacerda");

        Cliente c2 = new Cliente();
        c2.setId(1);
        c2.setNome("Breno Yuri");

        expectedClientesList.add(c1);
        expectedClientesList.add(c2);

        BDDMockito
            .given(entityManager.createQuery("SELECT c FROM Cliente c ORDER BY c.nome", Cliente.class))
            .willReturn(clienteQuery);

        BDDMockito
            .given(clienteQuery.setHint(AvailableHints.HINT_CACHEABLE, true))
            .willReturn(clienteQuery);

        BDDMockito
            .given(clienteQuery.getResultList())
            .willReturn(expectedClientesList);

        List<Cliente> clientesListFromRepository = repository.findAll();

        assertEquals(
                expectedClientesList, 
                clientesListFromRepository);

    }

    @Test
    void deveRetornarFalseAoInvocarExistsByIdQuandoNaoExistir() {

        int clienteId = 1;

        BDDMockito
            .given(entityManager.createQuery(CLIENTE_EXISTS_BY_ID_QUERY, Boolean.class))
            .willReturn(booleanQuery);

        BDDMockito
            .given(booleanQuery.setParameter(1, clienteId))
            .willReturn(booleanQuery);

        BDDMockito
            .given(booleanQuery.getSingleResult())
            .willThrow(NoResultException.class);

        assertFalse(repository.existsById(clienteId));

    }

    @Test
    void deveRetornarTrueAoInvocarExistsByIdQuandoExistir() {

        int clienteId = 1;

        BDDMockito
            .given(entityManager.createQuery(CLIENTE_EXISTS_BY_ID_QUERY, Boolean.class))
            .willReturn(booleanQuery);

        BDDMockito
            .given(booleanQuery.setParameter(1, clienteId))
            .willReturn(booleanQuery);

        BDDMockito
            .given(booleanQuery.getSingleResult())
            .willReturn(true);

        assertTrue(repository.existsById(clienteId));

    }    

}
































