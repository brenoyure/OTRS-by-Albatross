package br.albatross.otrs.domain.models.queue;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.Valid;
import br.albatross.otrs.domain.models.groups.Group;

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
 * Representa as Filas de chamados, como: Nível 1, Nível 2, Lixo e etc.
 * 
 * @author breno.brito
 *
 */
@Entity @Table(name = "queue")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class Queue {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@Column(length = 200, unique = true, nullable = false)
	private String name;

	@Column(length = 250, unique = false, nullable = true)
	private String comments;

	@ManyToOne
	@JoinColumn(name = "group_id", nullable = false)	
	private Group group;

	@ManyToOne
	@JoinColumn(name = "valid_id", nullable = false)
	private Valid valid;

	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "change_time", nullable = false)
	private LocalDateTime changeTime;
	
}
