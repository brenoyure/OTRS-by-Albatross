/**
 * 
 */

const placeholder = 
`Ao digitar: Prezado Fornecedor $fornecedor.nome, falamos da $cliente.descricao referente ao equipamento $numeroDeSerie.

Resultará em: Prezado Fornecedor Fornecedor LTDA, falamos da Matriz da Empresa Cliente LTDA referente ao equipamento AVCLX486.

Claro, ao enviar uma Solicitação de Garantia normalmente, os valores do template criado nesta página, serão substituídos pelos valores reais
que o usuário estará preenchendo na aba de 'Serviço de Garantia'.

`
document.getElementById('textArea-resultado-corpoDoEmailTemplate').setAttribute('placeholder', placeholder)

function imprimeAssuntoTemplateNoInputTextResultado() {
    let templateAssuntoInputText = document.getElementById('form-cadastroEmailPronto:inputText-assuntoDoEmailTemplate')
    let resultadoAssuntoInputText = document.getElementById('inputText-resultado-assuntoDoEmailTemplate')
    resultadoAssuntoInputText.value = fromTemplateToRealString(templateAssuntoInputText.value)
}

function imprimeTemplateNoTextAreaResultado() {
    let templateTextArea = document.getElementById('form-cadastroEmailPronto:textArea-corpoDoEmailTemplate')
    let resultadoTextArea = document.getElementById('textArea-resultado-corpoDoEmailTemplate')
    resultadoTextArea.value = fromTemplateToRealString(templateTextArea.value)
}
