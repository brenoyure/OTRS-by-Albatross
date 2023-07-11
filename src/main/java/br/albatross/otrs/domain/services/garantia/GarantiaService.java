package br.albatross.otrs.domain.services.garantia;

import jakarta.validation.Valid;

public interface GarantiaService<TipoDoEmail> {

	void enviarEmail(@Valid TipoDoEmail email);

}
