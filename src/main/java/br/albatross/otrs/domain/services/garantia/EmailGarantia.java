package br.albatross.otrs.domain.services.garantia;

import br.albatross.otrs.domain.services.beans.Problema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class EmailGarantia {
	private Problema problema;
	private String body;
	private String to;
	private String subject;
}
