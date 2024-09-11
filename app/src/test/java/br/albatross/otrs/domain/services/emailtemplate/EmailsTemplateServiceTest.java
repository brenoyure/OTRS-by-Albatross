package br.albatross.otrs.domain.services.emailtemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.albatross.apis.email.Email;
import br.albatross.otrs.domain.models.cliente.DadosDoClienteDto;
import br.albatross.otrs.domain.models.fornecedor.DadosDoFornecedorDto;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamadoDto;
import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.models.garantia.apis.fornecedores.DadosDoFornecedor;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.domain.models.garantia.entidades.solicitacao.SolicitacaoDeGarantiaImpl;
import br.albatross.otrs.persistence.entities.cliente.Cliente;
import br.albatross.otrs.persistence.entities.problemas.DescricaoProblema;
import br.albatross.otrs.persistence.entities.problemas.Problema;

public class EmailsTemplateServiceTest {

    private SolicitacaoDeGarantia solicitacao;

    private EmailTemplateService emailProntoService;

    @BeforeEach
    void init() {
        emailProntoService = new EmailTemplateService();
        Problema problema = new Problema();
        problema.setTipo("Gabinete");

        DescricaoProblema descricaoProblema = new DescricaoProblema();
        descricaoProblema.setProblema(problema);
        descricaoProblema.setDescricaoDetalhada("Computador está ligando porém não apresenta imagem, solicitamos verificação");

        Cliente clienteEntity = new Cliente();
        clienteEntity.setId(1);
        clienteEntity.setNome("Empresa LTDA");
        clienteEntity.setDescricao("Matriz da Empresa LTDA");

        clienteEntity.setNumerosParaContato("(85)3386-4214, (85)3386-4416");
        clienteEntity.setEmailsParaContato("atendimento.cliente@mail.br, cliente.ltda@mail.br");

        clienteEntity.setLogradouro("Rua dos Escritores");
        clienteEntity.setNumero("150");
        clienteEntity.setBairro("Bairro dos Escrivões");
        clienteEntity.setEstado("Ceará");
        clienteEntity.setCidade("FortalCity");
        clienteEntity.setCep("60000-486");

        clienteEntity.setHorarioInicioDoExpediente(LocalTime.of(8, 0));
        clienteEntity.setHorarioFimDoExpediente(LocalTime.of(17, 0));

        clienteEntity.setPossuiHorarioDeAlmoco(true);
        clienteEntity.setInicioDoHorarioDeAlmoco(LocalTime.of(12, 0));
        clienteEntity.setFimDoHorarioDeAlmoco(LocalTime.of(13, 0));

        DadosDoCliente cliente = new DadosDoClienteDto(clienteEntity);
        DadosDoFornecedor fornecedor = new DadosDoFornecedorDto(1, "Fornecedor LTDA", "suporte.garantia@mail.fornecedor.br, atendimento.empresas@fornecedor.br", Set.of(1, 2, 3, 4, 5));

        DadosDoChamadoDto chamado = new DadosDoChamadoDto();
        chamado.setNumeroDoChamado("20240101000486");

        Email emailDeGarantia = new Email();

        solicitacao = new SolicitacaoDeGarantiaImpl();
        solicitacao.setEmailDeGarantia(emailDeGarantia);
        solicitacao.setDadosDoFornecedor(fornecedor);
        solicitacao.setDadosDoCliente(cliente);
        solicitacao.setDescricaoDoProblema(descricaoProblema);
        solicitacao.setNumeroDeSerie("AVCLX486");
        solicitacao.setChamado(chamado);

    }

    @Test
    void testaAGeracaoDoAssuntoDoEmailAPartirDoTemplate() {

        String assuntoTemplate = "[Ticket#$chamado.numeroDoTicket] Problema $problema.tipo $fornecedor.nome - $cliente.nome";
        String assuntoGeradoAPartirDoTemplate = emailProntoService.getFromTemplate(assuntoTemplate, solicitacao);

        String assuntoEsperado = "[Ticket#20240101000486] Problema Gabinete Fornecedor LTDA - Empresa LTDA";

        assertEquals(assuntoEsperado, assuntoGeradoAPartirDoTemplate);
        
    }

    @Test
    void testaAGeracaoDoCorpoDoEmailAPartirDoTemplate() {

        String corpoDoEmailTemplate = 
"""
Prezados,
Falamos do(a) $cliente.nome - $cliente.descricao,
Segue em anexo o formulário preenchido para abertura de solicitação de garantia para o equipamento: $numeroDeSerie.

Prezado atendente do fornecedor $fornecedor.nome: 

O formulário possui as informações necessárias sobre a solicitação, como, a descrição detalhada do problema,
horários disponíveis, como também, os dados de endereço.

As solicitações de garantia são realizadas através de um sistema interno de Solicitações de Garantia, com 
o objetivo de automatizar os envios de solicitações para diversos fornecedores.

Atenciosamente,
--
$cliente.nome - $cliente.descricao
$cliente.endereco.cidade/$cliente.endereco.estado
Serviço de Tecnologia da Informação ($cliente.nome)
$cliente.numerosParaContato
$cliente.emailsParaContato
""";

        String corpoDoEmailEsperado = 
"""
Prezados,
Falamos do(a) Empresa LTDA - Matriz da Empresa LTDA,
Segue em anexo o formulário preenchido para abertura de solicitação de garantia para o equipamento: AVCLX486.

Prezado atendente do fornecedor Fornecedor LTDA: 

O formulário possui as informações necessárias sobre a solicitação, como, a descrição detalhada do problema,
horários disponíveis, como também, os dados de endereço.

As solicitações de garantia são realizadas através de um sistema interno de Solicitações de Garantia, com 
o objetivo de automatizar os envios de solicitações para diversos fornecedores.

Atenciosamente,
--
Empresa LTDA - Matriz da Empresa LTDA
FortalCity/Ceará
Serviço de Tecnologia da Informação (Empresa LTDA)
(85)3386-4214, (85)3386-4416
atendimento.cliente@mail.br, cliente.ltda@mail.br
""";

       String corpoDoEmailGeradoAPartirDoTemplate = 
               emailProntoService.getFromTemplate(corpoDoEmailTemplate, solicitacao);

       assertEquals(corpoDoEmailEsperado, corpoDoEmailGeradoAPartirDoTemplate);

    }

}


































