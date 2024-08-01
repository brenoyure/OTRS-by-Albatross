package br.albatross.otrs.domain.services.garantia;

import br.albatross.otrs.cdi.SolicitacaoDeGarantiaFactoryBean;
import br.albatross.otrs.domain.dao.apis.chamados.ChamadosDao;
import br.albatross.otrs.domain.dao.apis.fornecedores.FornecedoresDao;
import br.albatross.otrs.domain.dao.apis.problemas.DescricaoProblemaDao;
import br.albatross.otrs.domain.exceptions.SolicitacaoDeGarantiaException;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.models.garantia.apis.problemas.DescricaoProblema;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.models.garantia.entidades.fornecedores.DadosDoFornecedorDto;
import br.albatross.otrs.domain.models.garantia.entidades.solicitacao.DadosParaNovaSolicitacaoDeGarantia;
import br.albatross.otrs.domain.services.apis.garantia.SolicitacaoDeGarantiaService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class SolicitacaoDeGarantiaServiceImpl implements SolicitacaoDeGarantiaService {

    @Inject
    private DescricaoProblemaDao descricaoProblemaDao;

    @Inject
    private ChamadosDao chamadosDao;

    @Inject
    private FornecedoresDao fornecedoresDao;

    @Inject
    private SolicitacaoDeGarantiaFactoryBean factoryBean;

    @Override
    @Transactional
    public SolicitacaoDeGarantia criarNovaSolicitacao(@Valid DadosParaNovaSolicitacaoDeGarantia solicitacao) {

        SolicitacaoDeGarantia solicitacaoDeGarantia = factoryBean.getSolicitacaoDeGarantia();

        DadosDoChamado chamado = 
                chamadosDao
                    .findById(solicitacao.getIdDoChamado())
                    .orElseThrow(() -> new SolicitacaoDeGarantiaException("Ticket com o Id informado não encontrado"));

        DescricaoProblema problema = 
                descricaoProblemaDao
                    .findById(solicitacao.getDescricaoProblemaId())
                    .orElseThrow(() -> new SolicitacaoDeGarantiaException("Descrição do Problema com o Id informado não encontrado"));

        DadosDoFornecedor fornecedor = 
                fornecedoresDao
                    .findById(solicitacao.getIdDoFornecedor()).map(DadosDoFornecedorDto::new)
                    .orElseThrow(() -> new SolicitacaoDeGarantiaException("Fornecedor com o Id informado não encontrado"));

        solicitacaoDeGarantia.setChamado(chamado);
        solicitacaoDeGarantia.setDescricaoDoProblema(problema);
        solicitacaoDeGarantia.setNumeroDeSerie(solicitacao.getNumeroDeSerie());
        solicitacaoDeGarantia.setDadosDoFornecedor(fornecedor);

        return solicitacaoDeGarantia;

    }

}
