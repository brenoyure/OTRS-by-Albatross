package br.albatross.otrs.domain.models.garantia.entidades.problemas;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = Problema.class)
public abstract class Problema_ {

	public static volatile SingularAttribute<Problema, Byte> id;

	/**
	 * Mouse, Teclado, Gabinete, Monitor
	 */
	public static volatile SingularAttribute<Problema, String> tipo;

}
