package br.albatross.otrs.domain.models.configitem.metamodel;

import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.configitem.ConfigItem;
import br.albatross.otrs.domain.models.configitem.ConfigItemVersion;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

/**
 * MetaModel da Entidade {@code XmlStorage} para a API de Criteria.
 * 
 * @author breno.brito
 *
 */
@StaticMetamodel(value = ConfigItemVersion.class)
public abstract class ConfigItemVersion_ {

	public static volatile SingularAttribute<ConfigItemVersion, Long> id;

	public static volatile SingularAttribute<ConfigItemVersion, String> name;

	public static volatile SingularAttribute<ConfigItemVersion, ConfigItem> configItem;

	public static volatile SingularAttribute<ConfigItemVersion, LocalDateTime> createTime;

}
