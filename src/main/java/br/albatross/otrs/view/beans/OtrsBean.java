package br.albatross.otrs.view.beans;

import br.albatross.otrs.domain.services.beans.ConfigItemBeanService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named @RequestScoped
public class OtrsBean {

	@Inject
	private ConfigItemBeanService service;

	@Getter @Setter
	private String numeroDeSerie;

	@Getter @Setter
	private String bm;
	
	public void buscarNumeroDeSeriePeloBm() {
		service
			.buscarNumeroDeSeriePorBm(bm)
			.ifPresent(NdeSerie -> this.setNumeroDeSerie(NdeSerie));
	}

}
