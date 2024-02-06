package br.albatross.otrs.domain.models.garantia.apis.chamado;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode(of = "idDoServico")
@AllArgsConstructor @NoArgsConstructor
public class DadosDoServicoDto implements DadosDoServico {

	private static final long serialVersionUID = 1L;

	private Integer idDoServico;
	private String  nomeDoServico;
	
}
