package br.albatross.otrs.domain.models.garantia.entidades.cliente;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDeEndereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class DadosDeEnderecoDto implements DadosDeEndereco {

    private static final long serialVersionUID = 1L;

    private String logradouro;
    private String numero;
    private String estado;
    private String cidade;
    private String cep;

    public DadosDeEnderecoDto(Cliente cliente) {
        this.logradouro = cliente.getLogradouro();
        this.numero = cliente.getNumero();
        this.estado = cliente.getEstado();
        this.cidade = cliente.getCidade();
        this.cep = cliente.getCep();
    }

}
