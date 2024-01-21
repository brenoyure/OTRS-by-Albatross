package br.albatross.otrs.domain.models.otrs.configitem;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.otrs.Valid;
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

/**
 * 
 * Representa o catalogo do CMDB de itens de configuração, como Computer, Hardware e etc.
 * 
 * 
 * @author breno.brito
 *
 */
@Entity @Table(name = "general_catalog")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class GeneralCatalog {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@Column(length = 100, nullable = false)
	private String generalCatalogClass;

	@Column(length = 100, nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "valid_id")
	private Valid valid;

	@Column(length = 200, nullable = true)
	private String comments;

	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "change_time", nullable = false)
	private LocalDateTime changeTime;
	
}
