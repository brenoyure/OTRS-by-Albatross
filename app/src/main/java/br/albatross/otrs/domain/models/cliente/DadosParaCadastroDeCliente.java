package br.albatross.otrs.domain.models.cliente;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DadosParaCadastroDeCliente {

    @NotBlank
    @Size(min = 1, max = 100)
    private String nome;

    @NotBlank
    @Size(min = 1, max = 255)
    private String descricao;

    @Size(max = 100)
    private String numerosParaContato;

    private String emailsParaContato;

    @NotBlank
    @Size(max = 100)
    private String logradouro;

    @NotBlank
    @Size(max = 55)
    private String numero;

    @NotBlank
    @Size(max = 55)
    private String bairro;    

    @NotBlank
    @Size(max = 55)
    private String estado;

    @NotBlank
    @Size(max = 55)
    private String cidade;    

    @NotBlank
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
