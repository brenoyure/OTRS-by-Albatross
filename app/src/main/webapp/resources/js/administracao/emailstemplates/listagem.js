/**
 * 
 */

const tableListagemEmailsModelo = document.getElementById('form-tableEmails:dataTable-emails')

let tableListagemEmailsModeloRevealedHtml = null

const tableListagemEmailsModeloOriginalHtml = tableListagemEmailsModelo.innerHTML

let count = 0;

tableListagemEmailsModelo.addEventListener('click', () => {

    if (count % 2 == 0) {

        if (tableListagemEmailsModeloRevealedHtml == null) {
            tableListagemEmailsModeloRevealedHtml = fromTemplateToRealString(tableListagemEmailsModeloOriginalHtml)
        }

        tableListagemEmailsModelo.innerHTML = tableListagemEmailsModeloRevealedHtml
        count++;
        return
    }

    tableListagemEmailsModelo.innerHTML = tableListagemEmailsModeloOriginalHtml
    count++;

})
