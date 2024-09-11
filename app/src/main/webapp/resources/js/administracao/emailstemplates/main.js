/**
 * 
 */

solicitacaoDeGarantia = {

    numeroDeSerie: 'AVCLX486',

    problema: {
        tipo: 'Gabinete',
        descricao: 'Computador Liga porém não apresenta imagem, solicitamos verificação'
    },

    chamado: {
        numeroDoTicket: '20240101000486'
    },

    cliente: {
        id: 1,
        nome: 'Empresa Cliente LTDA',
        descricao: 'Matriz da Empresa Cliente LTDA',
        numerosParaContato: '(85)3386-4214, (85)3386-4416',
        emailsParaContato: 'atendimento.cliente@mail.br, cliente.ltda@mail.br',
        endereco: {
            logradouro: 'Rua dos Escritores',
            numero: '150',
            bairro: 'Bairro dos Escrivões',
            estado: 'Ceará',
            cidade: 'FortalCity',
            cep: '60000-486'
        },

        horarios: {
            inicioDoExpediente: '08:00',
            fimDoExpediente: '17:00',
            inicioDoAlmoco: '12:00',
            fimDoAlmoco: '13:00'
        }

    },

    fornecedor: {
        id: 1,
        nome: 'Fornecedor LTDA',
        emailsParaContato: 'suporte.garantia@mail.fornecedor.br, atendimento.empresas@fornecedor.br'
    }

}

function fromTemplateToRealString(templateString) {
    let replaced = 
        templateString
            .replaceAll("$numeroDeSerie", 
                solicitacaoDeGarantia.numeroDeSerie)
            .replaceAll("$problema.tipo", 
                solicitacaoDeGarantia.problema.tipo)
            .replaceAll("$problema.descricao", 
                solicitacaoDeGarantia.problema.descricao)
            .replaceAll("$chamado.numeroDoTicket", 
                solicitacaoDeGarantia.chamado.numeroDoTicket)
            .replaceAll("$fornecedor.nome", 
                solicitacaoDeGarantia.fornecedor.nome)
            .replaceAll("$cliente.nome", 
                solicitacaoDeGarantia.cliente.nome)
            .replaceAll("$cliente.descricao", 
                solicitacaoDeGarantia.cliente.descricao)
            .replaceAll("$cliente.numerosParaContato", 
                solicitacaoDeGarantia.cliente.numerosParaContato)
            .replaceAll("$cliente.emailsParaContato", 
                solicitacaoDeGarantia.cliente.emailsParaContato)
            .replaceAll("$cliente.endereco.logradouro", 
                solicitacaoDeGarantia.cliente.endereco.logradouro)
            .replaceAll("$cliente.endereco.numero", 
                solicitacaoDeGarantia.cliente.endereco.numero)
            .replaceAll("$cliente.endereco.bairro", 
                solicitacaoDeGarantia.cliente.endereco.bairro)
            .replaceAll("$cliente.endereco.estado", 
                solicitacaoDeGarantia.cliente.endereco.estado)
            .replaceAll("$cliente.endereco.cidade", 
                solicitacaoDeGarantia.cliente.endereco.cidade)
            .replaceAll("$cliente.endereco.cep", 
                solicitacaoDeGarantia.cliente.endereco.cep)
            .replaceAll("$cliente.horarios.inicioDoExpediente", 
                solicitacaoDeGarantia.cliente.horarios.inicioDoExpediente)
            .replaceAll("$cliente.horarios.fimDoExpediente", 
                solicitacaoDeGarantia.cliente.horarios.fimDoExpediente)
            .replaceAll("$cliente.horarios.inicioDoAlmoco", 
                solicitacaoDeGarantia.cliente.horarios.inicioDoAlmoco)
            .replaceAll("$cliente.horarios.fimDoAlmoco", 
                solicitacaoDeGarantia.cliente.horarios.fimDoAlmoco);

    return replaced;
}
