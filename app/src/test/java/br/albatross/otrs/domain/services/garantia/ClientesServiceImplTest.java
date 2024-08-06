package br.albatross.otrs.domain.services.garantia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;

import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.Cliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosParaCadastroDeNovoCliente;
import br.albatross.otrs.repositories.api.ClienteRepository;
import jakarta.validation.ValidationException;

@ExtendWith(MockitoExtension.class)
@DisplayName("Implementação da interface ClienteService")
class ClientesServiceImplTest {

    @Mock
    private ClienteRepository repository;

    @Captor
    private ArgumentCaptor<Cliente> clienteCaptor;    

    @Mock
    private Cliente cliente;

    @InjectMocks
    private ClientesServiceImpl service;

    private DadosParaCadastroDeNovoCliente dto;

    @BeforeEach
    void init() {

        dto = new DadosParaCadastroDeNovoCliente();
        dto.setNome("Empresa XPTO");
        dto.setDescricao("Descrição da Empresa XPTO");
        dto.setPossuiHorarioDeAlmoco(false);
        dto.setHorarioInicioDoExpediente(LocalTime.of(8, 0));
        dto.setHorarioFimDoExpediente(LocalTime.of(17, 0));

    }
    
    @Test
    @DisplayName("Lança ValidationException se outro cliente com o nome informado já existir")
    void deveLancarValidationExceptionSeNomeDoClienteJaExistir() {

        BDDMockito
            .given(repository.existsByNome(dto.getNome())).willReturn(true);

        ValidationException nomeValidationException = 
            Assertions.assertThrows(ValidationException.class, () -> service.cadastrarNovoCliente(dto));

        Assertions
            .assertTrue(() -> nomeValidationException.getMessage().contains("Já existe um cliente cadastrado com o nome informado"));

    }

    @Test
    @DisplayName("Lança ValidationException se outro cliente com a descrição informada já existir")
    void deveLancarValidationExceptionSeADescricaoDoClienteJaExistir() {

        BDDMockito
            .given(repository.existsByDescricao(dto.getDescricao())).willReturn(true);

        ValidationException descricaoValidationException = 
            Assertions.assertThrows(ValidationException.class, () -> service.cadastrarNovoCliente(dto));

        Assertions
            .assertEquals("Já existe um cliente cadastrado com a descrição informada", descricaoValidationException.getMessage());

    }

    @Test
    @DisplayName("Verifica se a entidade Cliente é corretamente preenchida com os dados obrigatórios do DTO")
    void verificaSeOsDadosDaEntidadeForamPreenchidosAntesDoRepositoryPersist() {

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
    @DisplayName("Lança ValidationException caso o cliente possua horário de almoço, mas um dos horários não foi informado")
    void deveLancarValidationExceptionCasoPossuaHorarioDeAlmocoPoremOsInformadosSaoInvalidos() {

        /*
         * Flag indicando que o Cliente possui horário de almoço
         */
        dto.setPossuiHorarioDeAlmoco(true);

        ValidationException horarioDeAlmocoValidationException = 
                Assertions
                    .assertThrows(ValidationException.class, () -> service.cadastrarNovoCliente(dto));

        String expectedMessage = 
                "Foi informado que o cliente Empresa XPTO possui horário de almoço, porém o(s) horário(s) de início ou fim não foram informados";

        assertEquals(expectedMessage, horarioDeAlmocoValidationException.getMessage());

    }

    @Test
    @DisplayName("Deve invocar o getReference() em seguida o remove(), apenas SE o Cliente com o Id informado existir")
    void deveInvocarOGetReferenceEoRemoveApenasSeOClienteExistirPeloId() {

        BDDMockito
            .given(repository.existsById(cliente.getId()))
            .willReturn(false);

        service
            .excluirClientePeloId(cliente.getId());

        BDDMockito
            .verify(repository, Mockito.never())
            .getReferenceById(cliente.getId());

        BDDMockito
            .verify(repository, Mockito.never())
            .remove(cliente);        

    }

}






























