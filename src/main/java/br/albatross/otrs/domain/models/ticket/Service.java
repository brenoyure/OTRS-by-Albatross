package br.albatross.otrs.domain.models.ticket;

import static jakarta.persistence.FetchType.LAZY;
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
 * Representa o tipo do Serviço (do ticket) como: Estação de Trabalho, <br/>Instalação
 * de Softwares, Impressoras e Scanners, Garantia de Fabricante e etc.
 * 
 * @author breno.brito
 *
 */
@Entity @Table(name = "service")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class Service {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@Column(length = 200, nullable = false, unique = true)
	private String name;

	@Column(length = 250, nullable = true, unique = false)
	private String comments;

	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "change_time", nullable = false)
	private LocalDateTime changeTime;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "valid_id", nullable = false)
	private Valid valid;
	
}
