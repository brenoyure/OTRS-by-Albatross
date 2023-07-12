package br.albatross.otrs.domain.models.otrs.ticket.state;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa os tipos de estados dos Tickets/Serviços como 'Novo', 'Aberto', 'Fechado' e etc.
 * 
 * @author breno.brito
 *
 */
@Entity @Table(name = "ticket_state_type")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class TicketStateType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@Column(length = 200, nullable = false, unique = true)
	private String name;

	@Column(length = 250, nullable = true)
	private String comments;

	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "change_time", nullable = false)
	private LocalDateTime changeTime;

}
