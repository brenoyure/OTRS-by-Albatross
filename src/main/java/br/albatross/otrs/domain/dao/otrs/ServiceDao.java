package br.albatross.otrs.domain.dao.otrs;

import static br.albatross.otrs.domain.models.otrs.ticket.Service_.id;

import java.util.List;

import br.albatross.otrs.domain.models.otrs.ticket.Service;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ServiceDao {

	@PersistenceContext(unitName = "otrsdb")
	private EntityManager entityManager;
	
	public List<Service> findAll() {
		var cb      =  entityManager.getCriteriaBuilder();
		var query   =  cb.createQuery(Service.class);
		query.from(Service.class);
		
		return entityManager
			       .createQuery(query)
			       .getResultList();
		
	}

	public List<Service> findAllValidServices(List<Integer> serviceIds) {
		var cb      =  entityManager.getCriteriaBuilder();
		var query   =  cb.createQuery(Service.class);
		var service =  query.from(Service.class);

		query
			.where(
		    		service.get(id)
		    		.in(serviceIds));

		return entityManager
			       .createQuery(query)
			       .getResultList();
	}
	
}
