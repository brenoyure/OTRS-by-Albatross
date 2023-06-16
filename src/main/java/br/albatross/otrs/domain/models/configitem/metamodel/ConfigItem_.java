package br.albatross.otrs.domain.models.configitem.metamodel;

import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.configitem.ConfigItem;
import br.albatross.otrs.domain.models.configitem.ConfigItemVersion;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = ConfigItem.class)
public abstract class ConfigItem_ {

	public static volatile SingularAttribute<ConfigItem, Long> id;

	public static volatile SingularAttribute<ConfigItem, String> configItemNumber;

	public static volatile SingularAttribute<ConfigItem, ConfigItemVersion> lastVersion;

	public static volatile SingularAttribute<ConfigItem, LocalDateTime> createTime;

	public static volatile SingularAttribute<ConfigItem, LocalDateTime> changeTime;

}
