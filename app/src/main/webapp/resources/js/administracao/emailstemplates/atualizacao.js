/**
 * 
 */

const currentURL = new URL(window.location.href)
const emailIdParam = currentURL.searchParams.get('emailId');

let botaoSalvarAlteracoes = document.getElementById('form-cadastroEmailPronto:btn-salvarAlteracoes')
let botaoDescartarAlteracoes = document.getElementById('form-cadastroEmailPronto:link-descartarAlteracoes')
let botaoExcluir = document.getElementById('btn-excluir')

let labelAntesDeSalvar = document.getElementById('label-confiraOsResultadosAntesDeSalvar')

if (emailIdParam == 1) {

    document.getElementById('inputText-resultado-assuntoDoEmailTemplate').remove();

    labelAntesDeSalvar.innerText = 'Para manter a integridade do sistema, não será possível alterar ou excluir o modelo atual'
    labelAntesDeSalvar.style.color = 'red'

    botaoExcluir.disabled = true
    botaoSalvarAlteracoes.disabled = true

    const inputs = document.getElementsByTagName('input')
    for (let i = 0; i < inputs.length; i++) {
        inputs.item(i).disabled = true
    }

} else {
    labelAntesDeSalvar.innerText = 'Confira os resultados acima e em seguida, salve as alterações'
}

