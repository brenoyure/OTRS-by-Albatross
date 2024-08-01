/**
 * 
 */

async function sayHello() {

    let select = document.getElementById("formulario-email-garantia:selectOne-DescricaoProblema");

    let descricaoProblemaId = select.value

    if (descricaoProblemaId == '') {
        setTextAreaDescricaoProblemaText('')
        return
    }

    await definirTextoDoTextAreaDescricaoProblema(descricaoProblemaId)

};

async function definirTextoDoTextAreaDescricaoProblema(descricaoProblemaId) {

    let problemasEndPoint = "#{request.contextPath}" + "/api/problemas/" + descricaoProblemaId;

    await fetch(problemasEndPoint).then(response => {

        response
            .json()
            .then((data) => {

                setTextAreaDescricaoProblemaText(data.descricaoDetalhada);

            })

    })

}

function setTextAreaDescricaoProblemaText(text) {
    let textAreaProblema = document.getElementById('formulario-email-garantia:textArea-DescricaoProblema')
    textAreaProblema.innerHTML = text
}
