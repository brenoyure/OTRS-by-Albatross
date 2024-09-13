package br.albatross.otrs.persistence.repositories.emailtemplate;

import java.util.List;

import br.albatross.otrs.domain.models.emailtemplate.DadosDoEmailTemplateDto;
import br.albatross.otrs.domain.models.emailtemplate.DadosParaAtualizacaoDeEmailTemplate;
import br.albatross.otrs.domain.models.emailtemplate.EmailTemplateComboBox;
import br.albatross.otrs.persistence.entities.emailtemplate.EmailTemplate;
import br.albatross.otrs.persistence.repositories.Repository;

public interface EmailTemplateRepository extends Repository<EmailTemplate, Integer> {

    boolean existsByDescricao(String descricao);
    boolean existsByDescricaoAndNotById(String descricao, Integer id);

    List<DadosDoEmailTemplateDto> findAllAsDtoOrderByAssunto();
    List<EmailTemplateComboBox> findAllAsEmailTemplateComboBoxOrderByDescricao();

    void update(DadosParaAtualizacaoDeEmailTemplate dadosAtualizados);

}
