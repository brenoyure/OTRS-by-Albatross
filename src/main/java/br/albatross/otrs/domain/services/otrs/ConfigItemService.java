package br.albatross.otrs.domain.services.otrs;

import java.util.Optional;

import br.albatross.otrs.domain.dao.otrs.ConfigItemDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ConfigItemService {

	@Inject
	private ConfigItemDao dao;

	public Optional<String> getNumeroDeSerieByBm(String bm) {
		return dao.findNumeroDeSerieByBm(bm);
	}

}
