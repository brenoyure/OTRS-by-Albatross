package br.albatross.otrs.domain.services;

import java.util.List;

import br.albatross.otrs.domain.dao.QueueDao;
import br.albatross.otrs.domain.models.queue.Queue;
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
