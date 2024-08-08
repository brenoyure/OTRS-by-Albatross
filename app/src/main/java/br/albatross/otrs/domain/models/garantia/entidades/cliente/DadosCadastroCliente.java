package br.albatross.otrs.domain.models.garantia.entidades.cliente;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosCadastroCliente {

    @NotBlank
    @Size(min = 1, max = 100)
    private String nome;

    @NotBlank
    @Size(min = 1, max = 255)
    private String descricao;

    @Size(max = 100)
    private String numerosParaContato;

    private String emailsParaContato;

    @Size(max = 100)
    private String logradouro;

    @Size(max = 55)
    private String numero;

    @Size(max = 55)
    private String bairro;    

    @Size(max = 55)
    private String estado;

    @Size(max = 55)
    private String cidade;    

    @Size(max = 13)
    private String cep;

    @NotNull
    private LocalTime horarioInicioDoExpediente;

    @NotNull
    private LocalTime horarioFimDoExpediente;

    @NotNull
    private Boolean possuiHorarioDeAlmoco;

    private LocalTime inicioDoHorarioDeAlmoco;

    private LocalTime fimDoHorarioDeAlmoco;

}
