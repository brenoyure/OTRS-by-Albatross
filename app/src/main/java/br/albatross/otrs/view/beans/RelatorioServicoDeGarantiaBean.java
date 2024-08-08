package br.albatross.otrs.view.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named @RequestScoped
public class RelatorioServicoDeGarantiaBean {

//	@Inject
//	private ServicoDeRelatorioDeServicosDeGarantia servicoDeRelatorio;
//
//	@Getter
//	private List<RelatorioServicoDeGarantia> relatorioGeral;
//
//	@Getter
//	private List<RelatorioServicoDeGarantia> relatorioMensal;
//
//	@Getter
//	private List<RelatorioServicoDeGarantia> relatorioAnual;
//
//	@Getter
//	private long totalGeral;
//	
//	@Getter
//	private long totalMensal;
//	
//	@Getter
//	private long totalAnual;

    @Inject
    private FacesContext facesContext;

//	@Transactional
	@PostConstruct
	void init() {

//		relatorioGeral  = servicoDeRelatorio.getRelatorio();
//		relatorioMensal = servicoDeRelatorio.getRelatorioMensal();
//		relatorioAnual  = servicoDeRelatorio.getRelatorioAnual();
//
//		totalGeral  = relatorioGeral.stream().mapToLong(RelatorioServicoDeGarantia::getTotal).sum();
//		totalMensal = relatorioMensal.stream().mapToLong(RelatorioServicoDeGarantia::getTotal).sum();
//		totalAnual  = relatorioAnual.stream().mapToLong(RelatorioServicoDeGarantia::getTotal).sum();

	    facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "/otrs");

	}

}
