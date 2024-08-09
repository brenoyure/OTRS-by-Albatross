package br.albatross.otrs.persistence.entities.problemas;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = Problema.class)
public abstract class Problema_ {

	public static volatile SingularAttribute<Problema, Short> id;

	/**
	 * Mouse, Teclado, Gabinete, Monitor
	 */
	public static volatile SingularAttribute<Problema, String> tipo;

}
