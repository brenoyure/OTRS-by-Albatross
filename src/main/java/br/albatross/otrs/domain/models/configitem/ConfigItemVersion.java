package br.albatross.otrs.domain.models.configitem;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

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

@Entity @Table(name = "configitem_version")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class ConfigItemVersion {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(length = 250, nullable = false)
	private String name;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "configitem_id")
	private ConfigItem configItem;

	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "change_time", nullable = false)
	private LocalDateTime changeTime;

}
