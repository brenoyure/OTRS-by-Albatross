package br.albatross.otrs.domain.models.queue;

import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.Valid;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

/**
 * Representa as Filas de chamados, como: Nível 1, Nível 2, Lixo e etc.
 * 
 * @author breno.brito
 *
 */
@StaticMetamodel(value = Queue.class)
public abstract class Queue_ {

	public static volatile SingularAttribute<Queue, Integer> id;

	public static volatile SingularAttribute<Queue, String> name;

	public static volatile SingularAttribute<Queue, String> comments;

	public static volatile SingularAttribute<Queue, Valid> valid;

	public static volatile SingularAttribute<Queue, LocalDateTime> createTime;

	public static volatile SingularAttribute<Queue, LocalDateTime> changeTime;
	
}
