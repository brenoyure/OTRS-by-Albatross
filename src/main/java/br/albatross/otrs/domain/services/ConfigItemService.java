package br.albatross.otrs.domain.services;

import java.util.Optional;

import br.albatross.otrs.domain.dao.ConfigItemDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class ConfigItemService {

	@Inject
	private ConfigItemDao dao;

	public Optional<String> getNumeroDeSerieByBm(String bm) {
		return dao.findNumeroDeSerieByBmNative(bm);
	}

}
