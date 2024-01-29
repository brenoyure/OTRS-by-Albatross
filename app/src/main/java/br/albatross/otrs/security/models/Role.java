package br.albatross.otrs.security.models;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Collection;
import java.util.HashSet;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "roles")
@EqualsAndHashCode(of = "id")
@Getter @Setter
@Cacheable
@NoArgsConstructor @AllArgsConstructor
public class Role {

	@Id	@GeneratedValue(strategy = IDENTITY)
	private int id;

	@Column(length = 55, unique = true, nullable = false)
	private String name;

	@ManyToMany(mappedBy = "roles", fetch = LAZY)
	private Collection<User> roles = new HashSet<>();

}
