package br.albatross.otrs.domain.services.fornecedores;

import br.albatross.otrs.domain.models.fornecedor.DadosParaAtualizacaoDeFornecedor;
import br.albatross.otrs.domain.models.fornecedor.DadosParaCadastroDeNovoFornecedor;
import br.albatross.otrs.persistence.entities.fornecedor.Fornecedor;
import br.albatross.otrs.persistence.repositories.fornecedor.FornecedorRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class FornecedoresServiceImplTest {

    @Mock
    private FornecedorRepository repository;

    @InjectMocks
    private FornecedoresServiceImpl service;

    @Test
    void deveLancarValidationExceptionCasoOutroFornecidorJaExistaComONomeInformado() {

        DadosParaCadastroDeNovoFornecedor dto =
                Mockito.mock(DadosParaCadastroDeNovoFornecedor.class);

        BDDMockito
                .given(repository.existsByNome(dto.getNome()))
                .willReturn(true);

        ValidationException validationException = Assertions
                .assertThrows(ValidationException.class, () -> service.cadastrarNovoFornecedor(dto));

        Assertions
                .assertEquals("Já existe outro Fornecedor cadastrado com o nome informado", validationException.getMessage());

    }

    @Test
    @DisplayName("Ao cadastrar, garante que os dados obrigatórios da entidade fornecedor serão preenchidos corretamente com os dados do DTO")
    void aoCadastrarFornecedorDeveGarantirQueOsDadosObrigatoriosDaEntidadeSejamPreenchidosAPartirDoDtoDeCadastro() {

        DadosParaCadastroDeNovoFornecedor dto =
                new DadosParaCadastroDeNovoFornecedor();
        dto.setNome("Fornecedor XPTO");
        dto.setEmails("xpto@mail.com, support.xpto@mail.com");
        dto.setIdsDosServicosDoFornecedorNoSistemaDeChamados(Set.of(99, 100, 101));

        Fornecedor fornecedorPersistReturn =
                Mockito.mock(Fornecedor.class);

        BDDMockito
                .given(repository.existsByNome(dto.getNome()))
                .willReturn(false);

        ArgumentCaptor<Fornecedor> fornecedorCaptor =
                ArgumentCaptor.forClass(Fornecedor.class);

        BDDMockito
                .given(repository.persist(fornecedorCaptor.capture()))
                .willReturn(fornecedorPersistReturn);

        service.cadastrarNovoFornecedor(dto);

        Fornecedor fornecedor = fornecedorCaptor.getValue();

        BDDMockito
                .verify(repository)
                .persist(fornecedor);

        Assertions.assertEquals(dto.getNome(), fornecedor.getNome());
        Assertions.assertEquals(dto.getEmails(), fornecedor.getEmails());
        Assertions.assertEquals(dto.getIdsDosServicosDoFornecedorNoSistemaDeChamados(), fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados());

    }

//    @Test
//    @DisplayName("Garante que o getReference do Repository não será chamado em caso do fornecedor não existir")
//    void naoDeveInvocarRepositoryGetReferenceNoCasoDoFornecedorComODadoIdNaoExistir() {
//
//        int fornecedorId = 1;
//
//        BDDMockito
//                .given(repository.existsById(fornecedorId))
//                .willReturn(false);
//
//        service.excluirFornecedorPeloId(fornecedorId);
//
//        BDDMockito
//                .verify(repository, BDDMockito.never())
//                .getReferenceById(fornecedorId);
//
//    }

//    @Test
//    @DisplayName("Garante a ordem de execução existsById, getReference e remove() ao excluir um Fornecedor")
//    void deveInvocarNaOrdemOsMetodosDoRepositoryExistsByIdGetReferenceERemove() {
//
//        int fornecedorId = 1;
//
//        Fornecedor mockedFornecedor =
//                Mockito.mock(Fornecedor.class);
//
//        BDDMockito
//                .given(repository.existsById(fornecedorId))
//                .willReturn(true);
//
//        BDDMockito
//                .given(repository.getReferenceById(fornecedorId))
//                        .willReturn(mockedFornecedor);
//
//        service
//                .excluirFornecedorPeloId(fornecedorId);
//
//        InOrder inOrder =
//                Mockito.inOrder(repository);
//
//        inOrder.verify(repository).existsById(fornecedorId);
//        inOrder.verify(repository).getReferenceById(fornecedorId);
//        inOrder.verify(repository).remove(mockedFornecedor);
//
//    }

    @Test
    @DisplayName("Ao atualizar, garante que a entidade Fornecedor será corretamente preenchida com os dados do DTO")
    void aoAtualizar_garanteQueOFornecedorSeraPreenchidoComOsDadosDoDto() {

        DadosParaAtualizacaoDeFornecedor dto =
                new DadosParaAtualizacaoDeFornecedor();
        dto.setId(1);
        dto.setNome("Fornecedor XPTO");
        dto.setEmails("xpto@mail.com, support.xpto@mail.com");
        dto.setIdsDosServicosDoFornecedorNoSistemaDeChamados(Set.of(99, 100, 101));

        ArgumentCaptor<Fornecedor> fornecedorCaptor =
                ArgumentCaptor.forClass(Fornecedor.class);

        Fornecedor mockedMergeReturn =
                Mockito.mock(Fornecedor.class);

        BDDMockito
                .given(repository.existsByNomeAndNotById(dto.getNome(), dto.getId()))
                .willReturn(false);

        BDDMockito
                .given(repository.merge(fornecedorCaptor.capture()))
                .willReturn(mockedMergeReturn);

        service.atualizarFornecedor(dto);

        Fornecedor fornecedor =
                fornecedorCaptor.getValue();

        Assertions.assertEquals(dto.getId(), fornecedor.getId());
        Assertions.assertEquals(dto.getNome(), fornecedor.getNome());
        Assertions.assertEquals(dto.getEmails(), fornecedor.getEmails());
        Assertions.assertEquals(dto.getIdsDosServicosDoFornecedorNoSistemaDeChamados(), fornecedor.getIdsDosServicosDoFornecedorNoSistemaDeChamados());

    }

}
