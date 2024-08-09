package br.albatross.otrs.domain.models.cliente;

import java.time.LocalTime;

import br.albatross.otrs.domain.models.garantia.apis.cliente.HorariosDoCliente;
import br.albatross.otrs.persistence.entities.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class HorariosDoClienteDto implements HorariosDoCliente {

    private static final long serialVersionUID = 1L;

    private LocalTime horarioInicioDoExpediente;
    private LocalTime horarioFimDoExpediente;

    private boolean possuiHorarioDeAlmoco;

    private LocalTime inicioDoHorarioDeAlmoco;
    private LocalTime fimDoHorarioDeAlmoco;

    @Override
    public boolean possuiHorarioDeAlmoco() {
        return possuiHorarioDeAlmoco;
    }

    public HorariosDoClienteDto(Cliente cliente) {
        this.horarioInicioDoExpediente = cliente.getHorarioInicioDoExpediente();
        this.horarioFimDoExpediente = cliente.getHorarioFimDoExpediente();
        this.possuiHorarioDeAlmoco = cliente.isPossuiHorarioDeAlmoco();
        this.inicioDoHorarioDeAlmoco = cliente.getInicioDoHorarioDeAlmoco();
        this.fimDoHorarioDeAlmoco = cliente.getFimDoHorarioDeAlmoco();
    }
    
}
