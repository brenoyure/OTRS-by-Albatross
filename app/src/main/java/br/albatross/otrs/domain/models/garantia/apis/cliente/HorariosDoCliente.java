package br.albatross.otrs.domain.models.garantia.apis.cliente;

import java.time.LocalTime;

public interface HorariosDoCliente {

    LocalTime getHorarioInicioDoExpediente();
    LocalTime getHorarioFimDoExpediente();

    boolean possuiHorarioDeAlmoco();

    LocalTime getInicioDoHorarioDeAlmoco();
    LocalTime getFimDoHorarioDeAlmoco();

}
