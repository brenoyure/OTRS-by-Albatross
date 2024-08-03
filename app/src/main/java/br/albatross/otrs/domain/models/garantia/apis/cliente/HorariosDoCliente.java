package br.albatross.otrs.domain.models.garantia.apis.cliente;

import java.io.Serializable;
import java.time.LocalTime;

public interface HorariosDoCliente extends Serializable {

    LocalTime getHorarioInicioDoExpediente();
    LocalTime getHorarioFimDoExpediente();

    boolean possuiHorarioDeAlmoco();

    LocalTime getInicioDoHorarioDeAlmoco();
    LocalTime getFimDoHorarioDeAlmoco();

}
