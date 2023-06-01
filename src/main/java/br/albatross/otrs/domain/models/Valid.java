package br.albatross.otrs.domain.models;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name = "valid")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class Valid {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Short id;

	@Column(length = 200, nullable = false, unique = true)
	private String name;

	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "change_time", nullable = false)
	private LocalDateTime changeTime;

}
