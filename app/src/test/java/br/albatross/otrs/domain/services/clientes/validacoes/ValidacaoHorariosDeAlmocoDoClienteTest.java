package br.albatross.otrs.domain.services.clientes.validacoes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import br.albatross.otrs.domain.models.cliente.DadosParaCadastroDeCliente;
import jakarta.validation.ValidationException;

/**
 * 
 * @author breno.brito
 * @see ValidacaoHorariosDeAlmocoDoCliente
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Validações relacionadas ao Horário de Almoço do Cliente")
class ValidacaoHorariosDeAlmocoDoClienteTest {

    private DadosParaCadastroDeCliente dto;
    
    @Spy
    private ValidacaoHorariosDeAlmocoDoCliente validacao;

    @BeforeEach
    void init() {
        dto = new DadosParaCadastroDeCliente();
        
        dto.setNome("Empresa XPTO");
        dto.setDescricao("Descrição da Empresa XPTO");
        dto.setPossuiHorarioDeAlmoco(true);

        dto.setHorarioInicioDoExpediente(LocalTime.of(8, 0));
        dto.setHorarioFimDoExpediente(LocalTime.of(17, 0));

    }

    @Test
    @DisplayName("Lança ValidationException caso o cliente possua horário de almoço, porém os horários de inicio e fim não foram informados")
    void deveLancarValidationExceptionCasoPossuaHorarioDeAlmocoPoremOsHorariosDeInicioEFimNaoForamInformados() {

        ValidationException exception = 
                assertThrows(ValidationException.class, () -> validacao.validar(dto));

        String expectedMessage = 
                "Foi informado que o cliente Empresa XPTO possui horário de almoço, porém o(s) horário(s) de início ou fim não foram informados";

        Assertions
            .assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    @DisplayName("Deve lançar ValidationException caso o cliente possua horário de almoço, porém apenas o horário de incio for informado")
    void deveLancarValidationExceptionCasoOClientePossuiHorarioDeAlmocoMasApenasOInicioFoiInformado() {
        
        dto.setInicioDoHorarioDeAlmoco(LocalTime.of(12, 0));

        ValidationException horarioDeAlmocoValidationException = 
                Assertions
                    .assertThrows(ValidationException.class, () -> validacao.validar(dto));

        String expectedMessage = 
                "Foi informado que o cliente Empresa XPTO possui horário de almoço, porém o(s) horário(s) de início ou fim não foram informados";

        assertEquals(expectedMessage, horarioDeAlmocoValidationException.getMessage());

    }

    @Test
    @DisplayName("Deve lançar ValidationException caso o cliente possua horário de almoço, porém apenas o horário de fim for informado")
    void deveLancarValidationExceptionCasoOClientePossuiHorarioDeAlmocoMasApenasOFimFoiInformado() {

        dto.setFimDoHorarioDeAlmoco(LocalTime.of(13, 0));

        ValidationException horarioDeAlmocoValidationException = 
                Assertions
                    .assertThrows(ValidationException.class, () -> validacao.validar(dto));

        String expectedMessage = 
                "Foi informado que o cliente Empresa XPTO possui horário de almoço, porém o(s) horário(s) de início ou fim não foram informados";

        assertEquals(expectedMessage, horarioDeAlmocoValidationException.getMessage());

    }

    @Test
    @DisplayName("Não deve lançar ValidationException caso o cliente possua horário de almoço, e os horários, de inicio e fim forem informados")
    void naoDeveLancarValidationExceptionCasoOClientePossuiHorarioDeAlmocoEOsHorariosDeInicioEFimForemInformados() {

        /*
         * Flag indicando que o Cliente possui horário de almoço
         */
        dto.setPossuiHorarioDeAlmoco(true);

        dto.setInicioDoHorarioDeAlmoco(LocalTime.of(12, 0));
        dto.setFimDoHorarioDeAlmoco(LocalTime.of(13, 0));

        Assertions
            .assertDoesNotThrow(() -> validacao.validar(dto));

    }

    @Test
    @DisplayName("Não deve lançar ValidationException caso for informado que o cliente possua horário de almoço e os horários, de inicio e fim, forem informados")
    void naoDeveLancarValidationExceptionCasoForInformadoQueOClienteNaoPossuiHorarioDeAlmocoEMesmoAssimOHorarioDeInicioEFimFoiPassadoNoDto() {

        /*
         * Flag indicando que o Cliente possui horário de almoço
         */
        dto.setPossuiHorarioDeAlmoco(false);

        dto.setInicioDoHorarioDeAlmoco(LocalTime.of(12, 0));
        dto.setFimDoHorarioDeAlmoco(LocalTime.of(13, 0));

        Assertions
            .assertDoesNotThrow(() -> validacao.validar(dto));

    }    

    @Test
    @DisplayName("Não deve lançar ValidationException caso o cliente não possua horário de almoço, e os horários, de inicio e fim não forem informados")
    void naoDeveLancarValidationExceptionCasoOClientePossuiHorarioDeAlmocoEOsHorariosDeInicioEFimNaoForemInformados() {

        /*
         * Flag indicando que o Cliente possui horário de almoço
         */
        dto.setPossuiHorarioDeAlmoco(false);

        Assertions
            .assertDoesNotThrow(() -> validacao.validar(dto));

    }

}
