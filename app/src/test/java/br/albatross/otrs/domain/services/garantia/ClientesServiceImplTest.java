package br.albatross.otrs.domain.services.garantia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.then;

import java.time.LocalTime;
import java.util.List;

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
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosParaAtualizacaoCadastralDoCliente;
import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosParaCadastroDeNovoCliente;
import br.albatross.otrs.domain.services.validacoes.clientes.ValidacaoAtualizacaoCliente;
import br.albatross.otrs.domain.services.validacoes.clientes.ValidacaoCadastroNovoCliente;
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

    @Mock
    private List<ValidacaoCadastroNovoCliente> validacoesNovoCliente;

    @Mock
    private List<ValidacaoAtualizacaoCliente> validacoesAtualizacaoCliente;

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
    @DisplayName("Ao cadastrar um novo cliente, deve lançar ValidationException se outro cliente com o nome informado já existir")
    void aoCadastrarNovoClienteDeveLancarValidationExceptionSeNomeDoClienteJaExistir() {

        BDDMockito
            .given(repository.existsByNome(dto.getNome())).willReturn(true);

        ValidationException nomeValidationException = 
            Assertions.assertThrows(ValidationException.class, () -> service.cadastrarNovoCliente(dto));

        Assertions
            .assertTrue(() -> nomeValidationException.getMessage().contains("Já existe outro cliente cadastrado com o nome informado"));

    }

    @Test
    @DisplayName("Ao atualizar um cliente, deve lançar ValidationException se outro cliente com o nome informado já existir")
    void aoAtualizarUmClienteDeveLancarValidationExceptionSeNomeDoClienteJaExistir() {

        DadosParaAtualizacaoCadastralDoCliente dadosMockados = 
                Mockito.mock(DadosParaAtualizacaoCadastralDoCliente.class);

        BDDMockito
            .given(repository.existsByNomeAndNotById(dadosMockados.getNome(), dadosMockados.getId()))
            .willReturn(true);

        ValidationException nomeValidationException = 
                Assertions.assertThrows(ValidationException.class, () -> service.atualizarCadastroDeCliente(dadosMockados));

            Assertions
                .assertTrue(() -> nomeValidationException.getMessage().contains("Já existe outro cliente cadastrado com o nome informado"));        

    }

    @Test
    @DisplayName("Ao cadastrar, deve lançar ValidationException se outro cliente com a descrição informada já existir")
    void aoCadastrarNovoClienteDeveLancarValidationExceptionSeADescricaoDoClienteJaExistir() {

        BDDMockito
            .given(repository.existsByDescricao(dto.getDescricao())).willReturn(true);

        ValidationException descricaoValidationException = 
            Assertions.assertThrows(ValidationException.class, () -> service.cadastrarNovoCliente(dto));

        Assertions
            .assertEquals("Já existe outro cliente cadastrado com a descrição informada", descricaoValidationException.getMessage());

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
    @DisplayName("Lança ValidationException caso o cliente possua horário de almoço, porém os horários de inicio e fim não foram informados")
    void deveLancarValidationExceptionCasoPossuaHorarioDeAlmocoPoremOsHorariosDeInicioEFimNaoForamInformados() {

        /*
         * Flag indicando que o Cliente possui horário de almoço
         */
        dto.setPossuiHorarioDeAlmoco(true);

        ValidationException horarioDeAlmocoValidationException = 
                Assertions
                    .assertThrows(ValidationException.class, () -> service.cadastrarNovoCliente(dto));

        /*
         * Mensagem de Erro esperada
         */
        String expectedMessage = 
                "Foi informado que o cliente Empresa XPTO possui horário de almoço, porém o(s) horário(s) de início ou fim não foram informados";

        assertEquals(expectedMessage, horarioDeAlmocoValidationException.getMessage());

    }

    @Test
    @DisplayName("Deve lançar ValidationException caso o cliente possua horário de almoço, porém apenas o horário de incio for informado")
    void deveLancarValidationExceptionCasoOClientePossuiHorarioDeAlmocoMasApenasOInicioFoiInformado() {
        
        /*
         * Flag indicando que o Cliente possui horário de almoço
         */
        dto.setPossuiHorarioDeAlmoco(true);
        dto.setInicioDoHorarioDeAlmoco(LocalTime.of(12, 0));

        ValidationException horarioDeAlmocoValidationException = 
                Assertions
                    .assertThrows(ValidationException.class, () -> service.cadastrarNovoCliente(dto));

        String expectedMessage = 
                "Foi informado que o cliente Empresa XPTO possui horário de almoço, porém o(s) horário(s) de início ou fim não foram informados";

        assertEquals(expectedMessage, horarioDeAlmocoValidationException.getMessage());        

    }

    @Test
    @DisplayName("Deve lançar ValidationException caso o cliente possua horário de almoço, porém apenas o horário de fim for informado")
    void deveLancarValidationExceptionCasoOClientePossuiHorarioDeAlmocoMasApenasOFimFoiInformado() {

        /*
         * Flag indicando que o Cliente possui horário de almoço
         */
        dto.setPossuiHorarioDeAlmoco(true);
        dto.setFimDoHorarioDeAlmoco(LocalTime.of(13, 0));

        ValidationException horarioDeAlmocoValidationException = 
                Assertions
                    .assertThrows(ValidationException.class, () -> service.cadastrarNovoCliente(dto));

        String expectedMessage = 
                "Foi informado que o cliente Empresa XPTO possui horário de almoço, porém o(s) horário(s) de início ou fim não foram informados";

        assertEquals(expectedMessage, horarioDeAlmocoValidationException.getMessage());        

    }

}
