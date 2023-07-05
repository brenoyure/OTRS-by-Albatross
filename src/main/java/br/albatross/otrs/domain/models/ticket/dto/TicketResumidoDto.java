package br.albatross.otrs.domain.models.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public final class TicketResumidoDto {

	private Long   id;
	private String title;
	private String ticketNumber;
	private String serviceName;
	private String customerUserId;
	private String login;
	private String firstName;
	private String lastName;

}
