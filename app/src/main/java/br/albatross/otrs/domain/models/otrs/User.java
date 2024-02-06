package br.albatross.otrs.domain.models.otrs;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoResponsavelPeloChamado;
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
 * Representa os usuários do OTRS.
 * 
 * @author breno.brito
 *
 */
@Entity(name = "OtrsUser") @Table(name = "users")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class User implements Serializable, DadosDoResponsavelPeloChamado {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = IDENTITY)
	private Integer id;

	@Column(length = 200, nullable = false, unique = true)
	private String login;

	@Column(length = 64, nullable = false)
	private String pw;

	@Column(length = 50)
	private String title;

	@Column(name = "first_name", length = 100, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "valid_id", nullable = false)
	private Valid valid;

	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "change_time", nullable = false)
	private LocalDateTime changeTime;

	@Override
	public String getNomeCompleto() {
		return this.firstName.concat(" ").concat(lastName);
	}

}
