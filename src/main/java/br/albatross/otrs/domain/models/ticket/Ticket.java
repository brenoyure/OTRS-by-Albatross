package br.albatross.otrs.domain.models.ticket;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.queue.Queue;
import br.albatross.otrs.domain.models.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name = "ticket")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class Ticket {

	@Id @GeneratedValue(strategy = IDENTITY)
	private Integer id;

	/**
	 * Representa o nº do ticket, geralmente no padrão de<br/>começando com a da data ao
	 * contrário, por exemplo 2023060182000486.
	 */
	private String ticketNumber;
	
	/**
	 * Representa o título/assunto do Ticket.
	 */
	private String title;
	
	/**
	 * Representa a Fila do Ticket/Chamado, como: Nível 1, Nível 2, Lixo e etc.
	 */
	private Queue queue;

	/**
	 * Representa o tipo do Ticket: como Incidente, Solicitação de Serviço, Problema
	 * e etc.
	 */
	private TicketType type;
	
	/**
	 * Representa o tipo do Serviço (do ticket) como: Estação de Trabalho, <br/>
	 * Instalação de Softwares, Impressoras e Scanners, Garantia de Fabricante e
	 * etc.
	 */
	private Service service;
	
	/**
	 * <p>Representa o "usuário" que <strong>criou</strong> o ticket (chamado).</p>
	 * Por exemplo no caso do chamado ter chegado por e-mail, o user_id será sempre 1, o <em>root</em> do OTRS, ou seja o próprio OTRS).
	 */
	private User user;
	
	/**
	 * <p>Representa o usuário que está como responsável pelo ticket/chamado.</p>
	 * <p>No caso de chamados abertos via e-mail ou que na abertura manual,<br/> na opção de <em>Novo Chamado via Fone</em>, por exemplo,
	 * em que o responsável não é especificado, o responsável atribuído será o de id 1, ou seja o próprio <em>root</em> do OTRS.</p>
	 */
	private User responsibleUser;

	/**
	 * <p>Representa o estado atual, de maneira detalhada, do ticket.</p>
	 * <p>Por exemplo: <em>novo, aberto, fechado com/sem êxito, aguardando usuário/fornecedor.</em></p>
	 */
	private TicketState ticketState;
	
	/**
	 * Representa o login, geralmente no padrão de <em>nome.sobrenome</em>, o usuário do ticket/chamado.
	 */
	private String customerUserId;
	
	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "change_time", nullable = false)
	private LocalDateTime changeTime;
	
}
