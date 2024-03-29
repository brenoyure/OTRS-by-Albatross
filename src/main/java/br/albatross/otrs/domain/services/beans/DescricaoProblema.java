package br.albatross.otrs.domain.services.beans;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

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

@Entity @Table(name = "descricao_problema")
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class DescricaoProblema implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = IDENTITY)
	private Integer id;

	/**
	 * Mouse - Problema no Scroll, Monitor - Tela Piscando.
	 */
	@Column(name="descricao_resumida", length = 55, unique = true, nullable = false)
	private String descricaoResumida;

	/**
	 * Mouse com problema no Scroll, Monitor com tela piscando...
	 */
	@Column(name="descricao_detalhada", length = 255, unique = true, nullable = false)
	private String descricaoDetalhada;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "fk_problema_id")
	private Problema problema;

}
