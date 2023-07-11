package br.albatross.otrs.domain.services.otrs;

import java.util.List;

import br.albatross.otrs.domain.dao.otrs.QueueDao;
import br.albatross.otrs.domain.models.otrs.queue.Queue;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class QueueService {

	@Inject
	private QueueDao dao;

	public List<Queue> listarTodos() {
		return dao.findAll();
	}

}
