package br.albatross.otrs.domain.services.garantia;

import jakarta.validation.Valid;

public interface GarantiaService<T> {

	void enviarEmail(@Valid T t);

}
