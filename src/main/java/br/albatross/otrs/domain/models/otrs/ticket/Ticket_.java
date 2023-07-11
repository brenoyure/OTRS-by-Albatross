package br.albatross.otrs.domain.models.otrs.ticket;

import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.otrs.User;
import br.albatross.otrs.domain.models.otrs.queue.Queue;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(value = Ticket.class)
public abstract class Ticket_ {

	public static volatile SingularAttribute<Ticket, Long> id;

	/**
	 * Representa o nº do ticket, geralmente no padrão de<br/>
	 * começando com a da data ao contrário, por exemplo 2023060182000486.
	 */
	public static volatile SingularAttribute<Ticket, String> ticketNumber;

	/**
	 * Representa o título/assunto do Ticket.
	 */
	public static volatile SingularAttribute<Ticket, String> title;

	/**
	 * Representa a Fila do Ticket/Chamado, como: Nível 1, Nível 2, Lixo e etc.
	 */
	public static volatile SingularAttribute<Ticket, Queue> queue;

	/**
	 * Representa o tipo do Ticket: como Incidente, Solicitação de Serviço, Problema
	 * e etc.
	 */
	public static volatile SingularAttribute<Ticket, TicketType> type;

	/**
	 * Representa o tipo do Serviço (do ticket) como: Estação de Trabalho, <br/>
	 * Instalação de Softwares, Impressoras e Scanners, Garantia de Fabricante e
	 * etc.
	 */
	public static volatile SingularAttribute<Ticket, Service> service;

	/**
	 * <p>
	 * Representa o "usuário" que <strong>criou</strong> o ticket (chamado).
	 * </p>
	 * Por exemplo no caso do chamado ter chegado por e-mail, o user_id será sempre
	 * 1, o <em>root</em> do OTRS, ou seja o próprio OTRS).
	 */
	public static volatile SingularAttribute<Ticket, User> user;

	/**
	 * <p>
	 * Representa o usuário que está como responsável pelo ticket/chamado.
	 * </p>
	 * <p>
	 * No caso de chamados abertos via e-mail ou que na abertura manual,<br/>
	 * na opção de <em>Novo Chamado via Fone</em>, por exemplo, em que o responsável
	 * não é especificado, o responsável atribuído será o de id 1, ou seja o próprio
	 * <em>root</em> do OTRS.
	 * </p>
	 */
	public static volatile SingularAttribute<Ticket, User> responsibleUser;

	/**
	 * <p>
	 * Representa o estado atual, de maneira detalhada, do ticket.
	 * </p>
	 * <p>
	 * Por exemplo: <em>novo, aberto, fechado com/sem êxito, aguardando
	 * usuário/fornecedor.</em>
	 * </p>
	 */
	public static volatile SingularAttribute<Ticket, TicketState> ticketState;

	/**
	 * Representa o login, geralmente no padrão de <em>nome.sobrenome</em>, o
	 * usuário do ticket/chamado.
	 */
	public static volatile SingularAttribute<Ticket, String> customerUserId;

	public static volatile SingularAttribute<Ticket, LocalDateTime> createTime;

	public static volatile SingularAttribute<Ticket, LocalDateTime> changeTime;

}
