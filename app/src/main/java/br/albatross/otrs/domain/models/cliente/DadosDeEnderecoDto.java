package br.albatross.otrs.domain.models.cliente;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDeEndereco;
import br.albatross.otrs.persistence.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class DadosDeEnderecoDto implements DadosDeEndereco {

    private static final long serialVersionUID = 1L;

    private String logradouro;
    private String numero;
    private String bairro;
    private String estado;
    private String cidade;
    private String cep;

    public DadosDeEnderecoDto(Cliente cliente) {
        this.logradouro = cliente.getLogradouro();
        this.numero = cliente.getNumero();
        this.bairro = cliente.getBairro();
        this.estado = cliente.getEstado();
        this.cidade = cliente.getCidade();
        this.cep = cliente.getCep();
    }

}
