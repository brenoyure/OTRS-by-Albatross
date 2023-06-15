package br.albatross.otrs.domain.models.ticket;

import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.Valid;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = TicketState.class)
public abstract class TicketState_ {

	public static volatile SingularAttribute<TicketState, Byte> id;

	public static volatile SingularAttribute<TicketState, String> name;

	public static volatile SingularAttribute<TicketState, String> comments;

	public static volatile SingularAttribute<TicketState, TicketStateType> ticketStateType;

	public static volatile SingularAttribute<TicketState, LocalDateTime> createTime;

	public static volatile SingularAttribute<TicketState, LocalDateTime> changeTime;

	public static volatile SingularAttribute<TicketState, Valid> valid;

}
