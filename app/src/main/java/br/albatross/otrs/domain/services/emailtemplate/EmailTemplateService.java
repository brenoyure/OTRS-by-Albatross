package br.albatross.otrs.domain.services.emailtemplate;

import java.util.Optional;

import br.albatross.otrs.domain.models.emailtemplate.DadosParaAtualizacaoDeEmailTemplate;
import br.albatross.otrs.domain.models.emailtemplate.DadosParaCadastroDeEmailTemplate;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import br.albatross.otrs.persistence.entities.emailtemplate.EmailTemplate;
import br.albatross.otrs.persistence.repositories.emailtemplate.EmailTemplateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

/**
<p>
    Você pode utilizar as variáveis abaixo para gerar <i>um corpo de e-mail template</i>, que pode ser reaproveitado para várias solicitações ou fornecedores.
</p>
<ul>
    <li>Número de Série do Equipamento: $numeroDeSerie</li>
    <li>Tipo do Problema: $problema.tipo</li>
    <li>Descrição Detalhada do Problema do Equipamento: $problema.descricao</li>
    <li>Número do Ticket no Sistema de Chamado: $chamado.numeroDoTicket</li>
    <li>Nome do Fornecedor: $fornecedor.nome</li>
    <li>Nome do Cliente: $cliente.nome</li>
    <li>Números para contato do Cliente: $cliente.numerosParaContato</li>
    <li>Emails para contato do Cliente: $cliente.emailsParaContato</li>
    <li>Endereço do Cliente: Rua ou Avenida: $cliente.endereco.logradouro</li>
    <li>Endereço do Cliente: Número: $cliente.endereco.numero</li>
    <li>Endereço do Cliente: Bairro: $cliente.endereco.bairro</li>
    <li>Endereço do Cliente: Estado: $cliente.endereco.estado</li>
    <li>Endereço do Cliente: Cidade: $cliente.endereco.cidade</li>
    <li>Endereço do Cliente: CEP: $cliente.endereco.cep</li>
    <li>Horários do Cliente: Início do Expediente: $cliente.horarios.inicioDoExpediente</li>
    <li>Horários do Cliente: Início do Almoço: $cliente.horarios.inicioDoAlmoco</li>
    <li>Horários do Cliente: Início do Fim: $cliente.horarios.fimDoAlmoco</li>
    <li>Horários do Cliente: Fim do Expediente: $cliente.horarios.fimDoExpediente</li>
</ul>
 */
@ApplicationScoped
public class EmailTemplateService {

    @Inject
    private EmailTemplateRepository repository;

    public void cadastrar(@Valid DadosParaCadastroDeEmailTemplate dados) {

        if (repository.existsByDescricao(dados.getDescricao())) {
            throw new ValidationException("Já existe outro Email Modelo cadastrado com a descrição informada");
        }

        EmailTemplate email = 
                new EmailTemplate(dados.getDescricao(), dados.getAssunto(), dados.getCorpoDoEmail());
        repository.persist(email);
    }

    public void atualizar(@Valid DadosParaAtualizacaoDeEmailTemplate dadosAtualizados) {

        if (!repository.existsById(dadosAtualizados.getId())) {
            throw new ValidationException("Email Modelo com o Id informado não existe");
        }

        if (repository.existsByDescricaoAndNotById(dadosAtualizados.getDescricao(), dadosAtualizados.getId())) {
            throw new ValidationException("Já existe outro Email Modelo cadastrado com a descrição informada");
        }

        repository.update(dadosAtualizados);

    }

    public String getFromTemplate(String template, SolicitacaoDeGarantia solicitacaoDeGarantia) {

        String replace =  template
                .replace("$numeroDeSerie", 
                        solicitacaoDeGarantia.getNumeroDeSerie())
                .replace("$problema.tipo", 
                        solicitacaoDeGarantia.getDescricaoDoProblema().getProblema().getTipo())
                .replace("$problema.descricao", 
                        solicitacaoDeGarantia.getDescricaoDoProblema().getDescricaoDetalhada())
                .replace("$chamado.numeroDoTicket", 
                        solicitacaoDeGarantia.getChamado().getNumeroDoChamado())
                .replace("$fornecedor.nome", 
                        solicitacaoDeGarantia.getDadosDoFornecedor().getNome())
                .replace("$cliente.nome", 
                        solicitacaoDeGarantia.getDadosDoCliente().getNome())
                .replace("$cliente.descricao", 
                        solicitacaoDeGarantia.getDadosDoCliente().getDescricao())
                .replace("$cliente.numerosParaContato", 
                        solicitacaoDeGarantia.getDadosDoCliente().getNumerosParaContato())
                .replace("$cliente.emailsParaContato", 
                        solicitacaoDeGarantia.getDadosDoCliente().getEmails())
                .replace("$cliente.endereco.logradouro", 
                        solicitacaoDeGarantia.getDadosDoCliente().getDadosDeEndereco().getLogradouro())
                .replace("$cliente.endereco.numero", 
                        solicitacaoDeGarantia.getDadosDoCliente().getDadosDeEndereco().getNumero())
                .replace("$cliente.endereco.bairro", 
                        solicitacaoDeGarantia.getDadosDoCliente().getDadosDeEndereco().getBairro())
                .replace("$cliente.endereco.estado", 
                        solicitacaoDeGarantia.getDadosDoCliente().getDadosDeEndereco().getEstado())
                .replace("$cliente.endereco.cidade", 
                        solicitacaoDeGarantia.getDadosDoCliente().getDadosDeEndereco().getCidade())
                .replace("$cliente.endereco.cep", 
                        solicitacaoDeGarantia.getDadosDoCliente().getDadosDeEndereco().getCep())
                .replace("$cliente.horarios.inicioDoExpediente", 
                        solicitacaoDeGarantia.getDadosDoCliente().getHorarios().getHorarioInicioDoExpediente().toString())
                .replace("$cliente.horarios.fimDoExpediente", 
                        solicitacaoDeGarantia.getDadosDoCliente().getHorarios().getHorarioFimDoExpediente().toString());
        if (solicitacaoDeGarantia.getDadosDoCliente().getHorarios().getInicioDoHorarioDeAlmoco() != null && solicitacaoDeGarantia.getDadosDoCliente().getHorarios().getFimDoHorarioDeAlmoco() != null) {
            replace = replace
                    .replace("$cliente.horarios.inicioDoAlmoco", 
                            Optional.ofNullable(solicitacaoDeGarantia.getDadosDoCliente().getHorarios().getInicioDoHorarioDeAlmoco().toString()).orElse(""))
                    .replace("$cliente.horarios.fimDoAlmoco", 
                            Optional.ofNullable(solicitacaoDeGarantia.getDadosDoCliente().getHorarios().getFimDoHorarioDeAlmoco().toString()).orElse(""));            
        }

        return replace;

    }

}
