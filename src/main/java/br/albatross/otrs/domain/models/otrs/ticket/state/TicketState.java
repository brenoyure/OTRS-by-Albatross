package br.albatross.otrs.domain.models.otrs.ticket.state;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.otrs.Valid;
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

@Entity @Table(name = "ticket_state")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class TicketState implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = IDENTITY)
	private Byte id;
	
	@Column(length = 200, nullable = false, unique = true)
	private String name;

	@Column(length = 250, nullable = true)
	private String comments;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "type_id", nullable = false)
	private TicketStateType ticketStateType;

	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "change_time", nullable = false)
	private LocalDateTime changeTime;
	
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "valid_id", nullable = false)
	private Valid valid;
	
}
