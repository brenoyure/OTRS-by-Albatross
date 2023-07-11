package br.albatross.otrs.domain.models.otrs.ticket;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServico;
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
public class Service implements Serializable, DadosDoServico {

	private static final long serialVersionUID = 1L;

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

	@Override
	public Integer getIdDoServico() {
		return this.id;
	}

	@Override
	public String getNomeDoServico() {
		return this.name;
	}

	@Override
	public void setIdDoServico(Integer id) {
		this.id = id;
	}

	@Override
	public void setNomeDoServico(String nomeDoServico) {
		this.name = nomeDoServico;
	}
	
}
