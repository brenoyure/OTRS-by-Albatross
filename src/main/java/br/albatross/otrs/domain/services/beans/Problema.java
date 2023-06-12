package br.albatross.otrs.domain.services.beans;

import br.albatross.otrs.domain.models.ticket.Service;
import br.albatross.otrs.domain.models.ticket.Ticket;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Problema {

	private String bm;
	private String numeroDeSerie; 
	private Ticket ticket;
	private Service service;
	private String descricao;

}
