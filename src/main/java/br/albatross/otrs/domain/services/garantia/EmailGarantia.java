package br.albatross.otrs.domain.services.garantia;

import java.io.File;
import java.io.Serializable;

import br.albatross.otrs.domain.models.ticket.Ticket;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmailGarantia implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String numeroDeSerie;

	@NotNull
	private Ticket ticket;

	@NotBlank
	private String subject;

	@NotBlank(message = "Descrição do Problema não pode ficar em branco.")
	private String body;

	@NotBlank @Email
	private String from = "breno.brito@albatross.github.br";

	@NotBlank
	private String to = "suporte.fabricante@empresa.br";

	@NotNull
	private File uploadedFile;

}
