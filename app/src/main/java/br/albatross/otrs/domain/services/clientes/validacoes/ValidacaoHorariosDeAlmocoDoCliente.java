package br.albatross.otrs.domain.services.clientes.validacoes;

import br.albatross.otrs.domain.models.cliente.DadosAtualizacaoCliente;
import br.albatross.otrs.domain.models.cliente.DadosCadastroCliente;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.ValidationException;

/**
 * 
 * <p>Validação referente aos horários de almoço.</p>
 * 
 * <p>Caso for informado que o Cliente possui horário de almoço, e algum desses horários ou ambos não 
 * forem informados, a validação falhará.</p>
 * 
 * @author breno.brito
 * 
 */
@RequestScoped
public class ValidacaoHorariosDeAlmocoDoCliente implements ValidacaoCadastroNovoCliente, ValidacaoAtualizacaoCliente {

    private void validarHorarios(DadosCadastroCliente dadosCliente) {
        if (dadosCliente.getPossuiHorarioDeAlmoco() && (dadosCliente.getInicioDoHorarioDeAlmoco() == null || dadosCliente.getFimDoHorarioDeAlmoco() == null)) {
            throw new ValidationException("Foi informado que o cliente " + dadosCliente.getNome() + " possui horário de almoço, porém o(s) horário(s) de início ou fim não foram informados");
        }
    }

    @Override
    public void validar(DadosCadastroCliente dadosDoNovoCliente) {
        validarHorarios(dadosDoNovoCliente);
    }

    @Override
    public void validar(DadosAtualizacaoCliente dadosDoCliente) {
        validarHorarios(dadosDoCliente);
    }

}
