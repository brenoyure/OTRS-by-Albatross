package br.albatross.otrs.domain.models.configitem.metamodel;

import br.albatross.otrs.domain.models.configitem.ConfigItemVersion;
import br.albatross.otrs.domain.models.configitem.XmlStorage;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

/**
 * MetaModel da Entidade {@code XmlStorage} para a API de Criteria.
 * 
 * @author breno.brito
 *
 */
@StaticMetamodel(value = XmlStorage.class)
public abstract class XmlStorage_ {

	public static volatile SingularAttribute<XmlStorage, Long> id;

	public static volatile SingularAttribute<XmlStorage, ConfigItemVersion> configItemVersion;

	public static volatile SingularAttribute<XmlStorage, String> xmlType;

	public static volatile SingularAttribute<XmlStorage, String> xmlContentKey;

	public static volatile SingularAttribute<XmlStorage, String> xmlContentValue;

}
