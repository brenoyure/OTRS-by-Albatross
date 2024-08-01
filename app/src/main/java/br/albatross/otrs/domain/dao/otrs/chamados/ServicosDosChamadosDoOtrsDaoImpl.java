package br.albatross.otrs.domain.dao.otrs.chamados;

import java.util.List;

import br.albatross.otrs.domain.dao.apis.chamados.ServicosDosChamadosDao;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServico;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServicoDto;
import br.albatross.otrs.domain.models.otrs.service.Service;
import br.albatross.otrs.domain.models.otrs.service.Service_;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Representa o Dao com a entidade Service(Serviço do Ticket) do Sistema de Chamados OTRS/Znuny.
 */
@RequestScoped
public class ServicosDosChamadosDoOtrsDaoImpl implements ServicosDosChamadosDao {

    @PersistenceContext(unitName = "otrsdb")
    private EntityManager entityManager;

    @Override
    public List<DadosDoServico> listarServicosDisponiveis() {

        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(DadosDoServico.class);
        var service = cq.from(Service.class);

        cq.select(
                cb.construct(DadosDoServicoDto.class, 
                                                      service.get(Service_.id),
                                                      service.get(Service_.name)));

        return entityManager.createQuery(cq).getResultList();

    }

}
