package br.albatross.otrs.domain.services.garantia;

import java.io.File;
import java.io.Serializable;

import br.albatross.otrs.domain.models.ticket.Ticket;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmailGarantia implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numeroDeSerie;
	private Ticket ticket;
	private String subject;
	private String body;
	private String from;
	private String to;
	private File uploadedFile;

}
