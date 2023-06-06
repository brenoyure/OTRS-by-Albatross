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

@Entity @Table(name = "configitem")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class ConfigItem {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(name = "configitem_number", length = 100, unique = true, nullable = false)
	private String configItemNumber;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "last_version_id")
	private ConfigItemVersion lastVersion;

	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "change_time", nullable = false)
	private LocalDateTime changeTime;

}
