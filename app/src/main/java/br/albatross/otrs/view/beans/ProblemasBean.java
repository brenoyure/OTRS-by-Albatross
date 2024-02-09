package br.albatross.otrs.view.beans;

import java.io.IOException;
import java.io.Serializable;

import br.albatross.otrs.domain.models.garantia.entidades.problemas.DescricaoProblema;
import br.albatross.otrs.domain.models.garantia.entidades.problemas.Problema;
import br.albatross.otrs.domain.services.beans.ProblemasServiceBean;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.Part;
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

	@Getter @Setter
	private Part uploadedFile;	

	public void salvarProblema(Problema problema) {
		serviceBean.salvarProblema(problema);
		resetarProblema();
	}

	public void salvarDescricaoProblema(DescricaoProblema descricaoProblema) throws IOException {

		if (uploadedFile != null) {
			descricaoProblema.setAnexoNome(uploadedFile.getSubmittedFileName());
			descricaoProblema.setAnexo(uploadedFile.getInputStream().readAllBytes());
		}

		serviceBean.salvarDescricaoProblema(descricaoProblema);
		resetarAtributos();

	}

	public void excluirDescricaoProblema(DescricaoProblema descricaoProblema) {
		serviceBean.removerDescricaoProblema(descricaoProblema);
		resetarDescricaoProblema();
	}

	private void resetarProblema() {
		this.problema = new Problema();
	}

	private void resetarDescricaoProblema() {
		this.descricaoProblema = new DescricaoProblema();
	}

	private void resetarAtributos() {
		this.descricaoProblema = new DescricaoProblema();
		this.problema = new Problema();
	}

}
