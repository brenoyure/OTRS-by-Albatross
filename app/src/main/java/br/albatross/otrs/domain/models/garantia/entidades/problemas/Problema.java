package br.albatross.otrs.domain.models.garantia.entidades.problemas;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name = "problema")
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Cacheable
public class Problema implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = IDENTITY)
	private Short id;

	/**
	 * Mouse, Teclado, Gabinete, Monitor
	 */
	@Column(length = 20, unique = true, nullable = false)
	@Getter @Setter
	private String tipo;

}
