package br.albatross.otrs.domain.models.cliente;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class DadosAtualizacaoCliente extends DadosCadastroCliente {

    @Positive
    private int id;

    public DadosAtualizacaoCliente(DadosDoCliente cliente) {
        this.id = cliente.getId();
        setNome(cliente.getNome());
        setDescricao(cliente.getDescricao());
        setNumerosParaContato(cliente.getNumerosParaContato());
        setEmailsParaContato(cliente.getEmails());
        setLogradouro(cliente.getDadosDeEndereco().getLogradouro());
        setNumero(cliente.getDadosDeEndereco().getNumero());
        setBairro(cliente.getDadosDeEndereco().getBairro());
        setEstado(cliente.getDadosDeEndereco().getEstado());
        setCidade(cliente.getDadosDeEndereco().getCidade());
        setCep(cliente.getDadosDeEndereco().getCep());

        setHorarioInicioDoExpediente(cliente.getHorarios().getHorarioInicioDoExpediente());
        setHorarioFimDoExpediente(cliente.getHorarios().getHorarioFimDoExpediente());

        setPossuiHorarioDeAlmoco(cliente.getHorarios().possuiHorarioDeAlmoco());

        setInicioDoHorarioDeAlmoco(cliente.getHorarios().getInicioDoHorarioDeAlmoco());
        setFimDoHorarioDeAlmoco(cliente.getHorarios().getFimDoHorarioDeAlmoco());

    }
    
}
