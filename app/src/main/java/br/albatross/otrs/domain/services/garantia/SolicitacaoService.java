package br.albatross.otrs.domain.services.garantia;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import br.albatross.apis.email.Email;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;

import jakarta.annotation.Resource;

import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;

import jakarta.inject.Inject;

import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.stream.JsonGenerator;

import jakarta.validation.Valid;

/**
*
* EJB Stateless responsável por colocar os <code>Email</code>s de Garantia na fila de mensagens
*
* @author breno.brito
*/
@Stateless
public class SolicitacaoService {

    @Inject @JMSConnectionFactory(value = "java:/jms/RemoteActiveMQConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:jboss/exported/jms/queue/OtrsEmailQueue")
    private Queue queue;

    /**
     * Envia o e-mail da solicitação de garantia em formato JSON para a fila.
     * @param solicitacaoDeGarantia
     */
    @Asynchronous
	public void solicitarGarantia(@Valid SolicitacaoDeGarantia solicitacaoDeGarantia) {

        try (StringWriter stringWriter = new StringWriter()) {
            try (JsonGenerator jsonGenerator = Json.createGenerator(stringWriter)) {

                Email email = 
                        solicitacaoDeGarantia.getEmailDeGarantia();                    

                jsonGenerator
                    .writeStartObject()

                    .write("assunto",         email.getAssunto())
                    .write("corpoDaMensagem", email.getCorpoDaMensagem())
                    .write("remetente",       email.getRemetente())
                    .write("destinatario",    email.getDestinatario());

                if (email.getCopiaPara() != null && !email.getCopiaPara().isBlank()) {
                    jsonGenerator.write("copiaPara", email.getCopiaPara());
                }

                if ( email.getAnexos() != null && !email.getAnexos().isEmpty() ) {

                    jsonGenerator.writeStartArray("anexos");

                    email.getAnexos().forEach(anexo -> {

                        jsonGenerator
                            .writeStartObject()
                            .write("nome", anexo.getNome())
                            .writeStartArray("arquivo");

                        for (byte b : anexo.getArquivo()) {
                            jsonGenerator.write(b);
                        }

                        jsonGenerator
                            .writeEnd()
                            .writeEnd();

                    });

                    jsonGenerator.writeEnd();

                }

                jsonGenerator.writeEnd();

            }

            String emailDeGarantiaAsJson = stringWriter.toString();

            JsonReader jsonReader = Json.createReader(new StringReader(emailDeGarantiaAsJson));
            JsonObject jsonObject = jsonReader.readObject();
            System.out.println(jsonObject.toString());

            context.createProducer().send(queue, emailDeGarantiaAsJson);

        } catch (IOException e) { throw new RuntimeException(e); }

	}

}
