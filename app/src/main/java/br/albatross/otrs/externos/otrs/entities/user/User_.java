package br.albatross.otrs.externos.otrs.entities.user;

import java.time.LocalDateTime;

import br.albatross.otrs.externos.otrs.entities.valid.Valid;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

/**
 * 
 * Representa os usuários do OTRS.
 * 
 * @author breno.brito
 *
 */
@StaticMetamodel(value = User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Integer> id;

	public static volatile SingularAttribute<User, String> login;

	public static volatile SingularAttribute<User, String> pw;

	public static volatile SingularAttribute<User, String> title;

	public static volatile SingularAttribute<User, String> firstName;

	public static volatile SingularAttribute<User, String> lastName;

	public static volatile SingularAttribute<User, Valid> valid;

	public static volatile SingularAttribute<User, LocalDateTime> createTime;

	public static volatile SingularAttribute<User, LocalDateTime> changeTime;

}
