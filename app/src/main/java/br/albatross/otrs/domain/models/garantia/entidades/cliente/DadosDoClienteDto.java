package br.albatross.otrs.domain.models.garantia.entidades.cliente;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDeEndereco;
import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDoCliente;
import br.albatross.otrs.domain.models.garantia.apis.cliente.HorariosDoCliente;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class DadosDoClienteDto implements DadosDoCliente {

    private int id;

    private String nome;

    private String descricao;

    private String numerosParaContato;

    private String emails;

    private DadosDeEndereco dadosDeEndereco;

    private HorariosDoCliente horarios;

    public DadosDoClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.descricao = cliente.getDescricao();
        this.numerosParaContato = cliente.getNumerosParaContato();
        this.emails = cliente.getEmailsParaContato();
        this.dadosDeEndereco = new DadosDeEnderecoDto(cliente);
        this.horarios = new HorariosDoClienteDto(cliente);
    }
    
}
