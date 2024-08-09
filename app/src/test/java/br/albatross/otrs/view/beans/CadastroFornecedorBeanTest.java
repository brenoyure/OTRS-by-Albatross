package br.albatross.otrs.view.beans;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.albatross.otrs.domain.models.fornecedor.DadosDoFornecedorDto;
import br.albatross.otrs.domain.models.fornecedor.DadosParaCadastroDeNovoFornecedor;
import br.albatross.otrs.domain.services.fornecedores.FornecedoresService;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;
import jakarta.validation.ValidationException;

@DisplayName("CadastroFornecedorBean JSF Bean Test")
@ExtendWith(MockitoExtension.class)
class CadastroFornecedorBeanTest {

    @Mock
    FornecedoresService service;

    @Mock
    FacesContext facesContext;

    @Mock
    UIViewRoot viewRoot;

    @Mock
    DadosDoFornecedorDto dadosDoFornecedor;

    @Mock
    DadosParaCadastroDeNovoFornecedor dto;

    @InjectMocks
    CadastroFornecedorBean bean;

    @Test
    @DisplayName("Ao cadastrar e o service lançar uma ValidationException, não deve chamar o getViewRoot do facesContext")
    void aoCadastrarEOServiceLancarUmaValidationExceptionNaoDeveChamarOViewRootDoFacesContext() {

        given(service.cadastrarNovoFornecedor(dto)).willThrow(ValidationException.class);

        bean.cadastrarFornecedor();

        verify(facesContext, BDDMockito.never()).getViewRoot();
        verify(viewRoot, BDDMockito.never()).getViewId();

    }

    @Test
    @DisplayName("Ao cadastrar deve chamar o getViewRoot do facesContext")
    void aoCadastrarDeveChamarOViewRootDoFacesContext() {

        given(facesContext.getViewRoot()).willReturn(viewRoot);
        given(service.cadastrarNovoFornecedor(dto)).willReturn(dadosDoFornecedor);

        bean.cadastrarFornecedor();

        verify(facesContext).getViewRoot();
        verify(viewRoot).getViewId();

    }

    @Test
    @DisplayName("Ao cadastrar e o service lançar uma ValidationException, deve incluir a msg da exception no FacesContext")
    void aoCadastrarEOServiceLancarUmaValidationExceptionDeveAdicionarAMensagemDaExceptionNoFacesContext() {

        given(service.cadastrarNovoFornecedor(dto)).willThrow(ValidationException.class);

        bean.cadastrarFornecedor();

        verify(facesContext, BDDMockito.never()).getViewRoot();
        verify(viewRoot, BDDMockito.never()).getViewId();

    }

}
























