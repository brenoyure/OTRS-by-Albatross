package br.albatross.otrs.domain.services.beans;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

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
public class Problema implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = IDENTITY)
	private Byte id;

	/**
	 * Mouse, Teclado, Gabinete, Monitor
	 */
	@Column(length = 20, unique = true, nullable = false)
	@Getter @Setter
	private String tipo;

}
