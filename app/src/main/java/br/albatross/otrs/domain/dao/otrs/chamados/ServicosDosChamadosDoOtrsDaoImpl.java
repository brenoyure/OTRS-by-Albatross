package br.albatross.otrs.domain.dao.otrs.chamados;

import static br.albatross.otrs.domain.models.otrs.service.Service_.id;
import static br.albatross.otrs.domain.models.otrs.service.Service_.name;

import java.util.List;

import br.albatross.otrs.domain.dao.apis.chamados.ServicosDosChamadosDao;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServico;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServicoDto;
import br.albatross.otrs.domain.models.otrs.service.Service;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

/**
 * Representa o Dao com a entidade Service(Serviço do Ticket) do Sistema de Chamados OTRS/Znuny.
 */
@RequestScoped
public class ServicosDosChamadosDoOtrsDaoImpl implements ServicosDosChamadosDao {

    @PersistenceContext(unitName = "otrsdb")
    private EntityManager entityManager;

    @Override
    public List<DadosDoServico> listarServicosDisponiveis() {

        CriteriaBuilder builder              =  entityManager.getCriteriaBuilder();
        CriteriaQuery<DadosDoServico> query  =  builder.createQuery(DadosDoServico.class);
        Root<Service> service                =  query.from(Service.class);

        query
            .select(
                builder.construct(DadosDoServicoDto.class, 
                                                      service.get(id),
                                                      service.get(name)))
            .orderBy(
                builder.asc(service.get(name))
            );

        return entityManager.createQuery(query).getResultList();

    }

}
