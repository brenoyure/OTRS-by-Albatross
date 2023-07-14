package br.albatross.otrs.domain.models.otrs.ticket;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoChamado;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoResponsavelPeloChamado;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoServico;
import br.albatross.otrs.domain.models.garantia.apis.chamado.DadosDoUsuarioCliente;
import br.albatross.otrs.domain.models.otrs.User;
import br.albatross.otrs.domain.models.otrs.queue.Queue;
import br.albatross.otrs.domain.models.otrs.service.Service;
import br.albatross.otrs.domain.models.otrs.ticket.state.TicketState;
import br.albatross.otrs.domain.models.otrs.ticket.type.TicketType;
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

@Entity @Table(name = "ticket")
@EqualsAndHashCode(of = "id")
@Getter @Setter
public class Ticket implements Serializable, DadosDoChamado {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = IDENTITY)
	private Long id;

	/**
	 * Representa o nº do ticket, geralmente no padrão de<br/>começando com a da data ao
	 * contrário, por exemplo 2023060182000486.
	 */
	@Column(name = "tn", length = 50, unique = true, nullable = false)
	private String ticketNumber;

	/**
	 * Representa o título/assunto do Ticket.
	 */
	@Column(length = 255)
	private String title;

	/**
	 * Representa a Fila do Ticket/Chamado, como: Nível 1, Nível 2, Lixo e etc.
	 */
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "queue_id", nullable = false)
	private Queue queue;

	/**
	 * Representa o tipo do Ticket: como Incidente, Solicitação de Serviço, Problema
	 * e etc.
	 */
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "type_id")
	private TicketType type;

	/**
	 * Representa o tipo do Serviço (do ticket) como: Estação de Trabalho, <br/>
	 * Instalação de Softwares, Impressoras e Scanners, Garantia de Fabricante e
	 * etc.
	 */
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "service_id", nullable = true)
	private Service service;

	/**
	 * <p>Representa o "usuário" que <strong>criou</strong> o ticket (chamado).</p>
	 * Por exemplo no caso do chamado ter chegado por e-mail, o user_id será sempre 1, o <em>root</em> do OTRS, ou seja o próprio OTRS).
	 */
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	/**
	 * <p>Representa o usuário que está como responsável pelo ticket/chamado.</p>
	 * <p>No caso de chamados abertos via e-mail ou que na abertura manual,<br/> na opção de <em>Novo Chamado via Fone</em>, por exemplo,
	 * em que o responsável não é especificado, o responsável atribuído será o de id 1, ou seja o próprio <em>root</em> do OTRS.</p>
	 */
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "responsible_user_id", nullable = false)
	private User responsibleUser;

	/**
	 * <p>Representa o estado atual, de maneira detalhada, do ticket.</p>
	 * <p>Por exemplo: <em>novo, aberto, fechado com/sem êxito, aguardando usuário/fornecedor.</em></p>
	 */
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "ticket_state_id", nullable = false)
	private TicketState ticketState;

	/**
	 * Representa o login, geralmente no padrão de <em>nome.sobrenome</em>, o usuário do ticket/chamado.
	 */
	@Column(name = "customer_user_id", length = 250)
	private String customerUserId;

	@Column(name = "create_time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "change_time", nullable = false)
	private LocalDateTime changeTime;

	@Override
	public String getNumeroDoChamado() {
		return ticketNumber;
	}

	@Override
	public DadosDoServico getDadosDoServico() {
		return service;
	}

	@Override
	public DadosDoResponsavelPeloChamado getDadosDoResponsavelPeloChamado() {
		return responsibleUser;
	}

	@Override
	public void setNumeroDoChamado(String numeroDoTicket) {
		this.ticketNumber = numeroDoTicket;
		
	}

	@Override
	public void setDadosDoServico(DadosDoServico servico) {
		this.service = (Service) servico;
	}

	@Override
	public void setDadosDoResponsavel(DadosDoResponsavelPeloChamado dadosDoResponsavel) {
		this.responsibleUser = (User) dadosDoResponsavel;
		
	}

	@Override
	public DadosDoUsuarioCliente getDadosDoUsuarioCliente() {
		return new DadosDoUsuarioCliente() {

			public String getNomeDoUsuarioCliente() { return customerUserId; }};

	}

}
