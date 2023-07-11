package br.albatross.otrs.domain.models.garantia.entidades.problemas;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = DescricaoProblema.class)
public abstract class DescricaoProblema_ {

	public static volatile SingularAttribute<DescricaoProblema, Integer> id;

	/**
	 * Mouse - Problema no Scroll, Monitor - Tela Piscando.
	 */
	public static volatile SingularAttribute<DescricaoProblema, String> descricaoResumida;

	/**
	 * Mouse com problema no Scroll, Monitor com tela piscando...
	 */
	public static volatile SingularAttribute<DescricaoProblema, String> descricaoDetalhada;

	public static volatile SingularAttribute<DescricaoProblema, Problema> problema;

}
