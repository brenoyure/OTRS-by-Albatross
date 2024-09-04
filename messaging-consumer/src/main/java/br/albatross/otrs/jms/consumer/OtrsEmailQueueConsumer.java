package br.albatross.otrs.jms.consumer;

import java.io.BufferedReader;
import java.io.StringReader;

import org.jboss.ejb3.annotation.ResourceAdapter;

import br.albatross.apis.email.Anexo;
import br.albatross.apis.email.Email;
import br.albatross.apis.email.ServicoDeEnvioDeEmail;
import jakarta.annotation.Resource;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.mail.Session;

/**
*
* EJB MessageDriven responsável por consumir a fila de mensageria.
*
* chamando o ServiçoDeEnvioDeEmail e despachando os emails.
*
* @author breno.brito
*/
@ResourceAdapter("remote-activemq-connection-factory")
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms.queue.OtrsEmailQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",   propertyValue = "jakarta.jms.Queue")})
public class OtrsEmailQueueConsumer implements MessageListener {

    @Inject
    private ServicoDeEnvioDeEmail servicoDeEmail;

    @Resource(lookup = "java:jboss/mail/OtrsMailSession")
    private Session mailSession;

    @Override
    public void onMessage(Message message) {

        try {

            StringReader stringReader = new StringReader(message.getBody(String.class));
            BufferedReader bufferedReader = new BufferedReader(stringReader);
            JsonReader jsonReader = Json.createReader(bufferedReader);

            JsonObject emailJsonObject = jsonReader.readObject();
            String assunto = emailJsonObject.getString("assunto");
            String corpoDaMensagem = emailJsonObject.getString("corpoDaMensagem");
            String remetente = emailJsonObject.getString("remetente");
            String destinatario = emailJsonObject.getString("destinatario");
            String copiaPara =emailJsonObject.getString("copiaPara");

            JsonArray anexosJsonArray = emailJsonObject.getJsonArray("anexos");

            Email email = new Email();
            email.setAssunto(assunto);
            email.setCorpoDaMensagem(corpoDaMensagem);
            email.setRemetente(remetente);
            email.setDestinatario(destinatario);
            email.setCopiaPara(copiaPara);

            anexosJsonArray.forEach(a -> {

                JsonObject anexoJsonObject = a.asJsonObject();
                JsonArray arquivoByteJsonArray = anexoJsonObject.getJsonArray("arquivo");

                byte[] arquivo = new byte[arquivoByteJsonArray.size()];
                for(int i = 0; i < arquivoByteJsonArray.size(); i++) {
                    arquivo[i] = (byte) arquivoByteJsonArray.getJsonNumber(i).intValueExact();
                }

                String nome = anexoJsonObject.getString("nome");
                Anexo anexo = new Anexo(nome, arquivo);
                email.adicionarAnexo(anexo);

            });

            servicoDeEmail.enviar(email, mailSession);

            jsonReader.close();
            bufferedReader.close();
            stringReader.close();

        } catch (Exception e) { throw new RuntimeException(e); }

    }

}
