package br.albatross.otrs.domain.services.validacoes.clientes;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.albatross.otrs.domain.models.garantia.entidades.cliente.DadosParaCadastroDeNovoCliente;
import br.albatross.otrs.domain.services.garantia.ClientesServiceImpl;
import br.albatross.otrs.repositories.api.ClienteRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testa se as validações do tipo ValidacaoCadastroNovoCliente são executadas ao cadastrar um novo Cliente")
class ValidacoesCadastroNovoClienteTest {

    @Mock
    private ClienteRepository repository;

    @Spy
    private List<ValidacaoCadastroNovoCliente> validacoesNovoCadastro = new ArrayList<>();

    @Mock
    private ValidacaoCadastroNovoCliente validacaoNovoCadastro1;

    @Mock
    private ValidacaoCadastroNovoCliente validacaoNovoCadastro2;

    @InjectMocks
    private ClientesServiceImpl service;

    @Test
    @DisplayName("Verifica se os validadores de cadastro de novo cliente são chamadas")
    void deveChamarOsValidadoresAoCadastrarNovoCliente() {

        DadosParaCadastroDeNovoCliente dto = new DadosParaCadastroDeNovoCliente();
        dto.setNome("Empresa XPTO");
        dto.setDescricao("Descrição da Empresa XPTO");
        dto.setPossuiHorarioDeAlmoco(false);
        dto.setHorarioInicioDoExpediente(LocalTime.of(8, 0));
        dto.setHorarioFimDoExpediente(LocalTime.of(17, 0));        

        validacoesNovoCadastro.add(validacaoNovoCadastro1);
        validacoesNovoCadastro.add(validacaoNovoCadastro2);

        service.cadastrarNovoCliente(dto);

        BDDMockito
            .then(validacaoNovoCadastro1)
            .should()
            .validar(dto);

        BDDMockito
            .then(validacaoNovoCadastro2)
            .should()
            .validar(dto);

    }

}
