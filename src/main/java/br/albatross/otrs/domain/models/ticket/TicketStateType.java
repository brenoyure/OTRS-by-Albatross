package br.albatross.otrs.domain.models.ticket;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.Valid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class TicketStateType {

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

	@ManyToOne
	@JoinColumn(name = "valid_id", nullable = false)
	private Valid valid;

}
