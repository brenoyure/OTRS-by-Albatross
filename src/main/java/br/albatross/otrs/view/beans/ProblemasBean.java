package br.albatross.otrs.view.beans;

import java.io.Serializable;

import br.albatross.otrs.domain.services.beans.DescricaoProblema;
import br.albatross.otrs.domain.services.beans.Problema;
import br.albatross.otrs.domain.services.beans.ProblemasServiceBean;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named @ViewScoped
public class ProblemasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject	@Getter
	private ProblemasServiceBean serviceBean;

	@Getter	@Setter
	private Problema problema = new Problema();

	@Getter	@Setter
	private DescricaoProblema descricaoProblema = new DescricaoProblema();

	public void salvarProblema() {
		serviceBean.salvarProblema(problema);
		problema = new Problema();
	}

	public void salvarDescricaoProblema() {
		serviceBean.salvarDescricaoProblema(descricaoProblema);
		descricaoProblema = new DescricaoProblema();
	}

	public void excluirDescricaoProblema(DescricaoProblema descricaoProblema) {
		serviceBean.removerDescricaoProblema(descricaoProblema);
		this.descricaoProblema = new DescricaoProblema();
	}

}
