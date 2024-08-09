package br.albatross.otrs.persistence.entities.cliente;

import java.time.LocalTime;

import br.albatross.otrs.domain.models.cliente.DadosCadastroCliente;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "clientes")
@Getter @Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, unique = true, nullable = false)
    private String nome;

    @Column(length = 255, unique = true, nullable = false)
    private String descricao;

    @Column(name = "numeros_para_contato", length = 255, unique = false, nullable = false)
    private String numerosParaContato;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "emails_para_contato", unique = false, nullable = false)
    private String emailsParaContato;

    @Column(length = 100, unique = false, nullable = true)
    private String logradouro;

    @Column(length = 55, unique = false, nullable = true)
    private String numero;

    @Column(length = 55, unique = false, nullable = true)
    private String bairro;    

    @Column(length = 55, unique = false, nullable = true)
    private String estado;

    @Column(length = 55, unique = false, nullable = true)
    private String cidade;

    @Column(length = 13, unique = false, nullable = true)
    private String cep;

    @Column(name = "horario_inicio_do_expediente", nullable = false)
    private LocalTime horarioInicioDoExpediente;

    @Column(name = "horario_fim_do_expediente", nullable = false)
    private LocalTime horarioFimDoExpediente;

    @Column(name = "possui_horario_de_almoco", nullable = false)
    private boolean possuiHorarioDeAlmoco;

    @Column(name = "inicio_do_horario_de_almoco", nullable = true)
    private LocalTime inicioDoHorarioDeAlmoco;

    @Column(name = "fim_do_horario_de_almoco", nullable = true)
    private LocalTime fimDoHorarioDeAlmoco;

    public Cliente(DadosCadastroCliente novosDados) {

        this.nome = novosDados.getNome();
        this.descricao = novosDados.getDescricao();
        this.numerosParaContato = novosDados.getNumerosParaContato();
        this.emailsParaContato = novosDados.getEmailsParaContato();

        this.logradouro = novosDados.getLogradouro();
        this.numero = novosDados.getNumero();
        this.bairro = novosDados.getBairro();
        this.estado = novosDados.getEstado();
        this.cidade = novosDados.getCidade();
        this.cep = novosDados.getCep();

        this.horarioInicioDoExpediente = novosDados.getHorarioInicioDoExpediente();
        this.horarioFimDoExpediente = novosDados.getHorarioFimDoExpediente();

        this.possuiHorarioDeAlmoco = novosDados.getPossuiHorarioDeAlmoco();

        if (possuiHorarioDeAlmoco) {
            this.inicioDoHorarioDeAlmoco = novosDados.getInicioDoHorarioDeAlmoco();
            this.fimDoHorarioDeAlmoco = novosDados.getFimDoHorarioDeAlmoco();
        }

    }

}
