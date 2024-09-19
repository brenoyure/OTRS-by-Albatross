package br.albatross.otrs.jms.consumer;

import static java.lang.System.lineSeparator;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import br.albatross.apis.email.Anexo;
import br.albatross.apis.email.Email;
import br.albatross.apis.email.ServicoDeEnvioDeEmail;

import jakarta.jms.JMSException;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;

/**
 * 
 * @author breno.brito
 */
@ExtendWith(MockitoExtension.class)
public class OtrsEmailQueueConsumerTest {

    @Mock
    private ServicoDeEnvioDeEmail mockedServicoDeEmail;

    @Mock
    private jakarta.mail.Session mockedSession;    

    @Mock
    private jakarta.jms.Message mockedJmsMessage;

    @Captor
    private ArgumentCaptor<Email> emailCaptor;

    @InjectMocks
    private OtrsEmailQueueConsumer queueConsumer;

    @Test
    @DisplayName("Conversão de String JSON para classe Email com CopiaPara e Dois Anexos")
    void testaAConversaoDoJsonParaClasseEmailComCopiaParaEDoisAnexos() throws IOException, JMSException, MessagingException {

        String emailJson = null;

        try (InputStream fis = new BufferedInputStream(new FileInputStream(new File(getClass().getClassLoader().getResource("emailJsonComCopiaParaEDoisAnexos.json").getFile())))) {

            try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {

                StringBuilder sb = new StringBuilder();

                for (String linha = br.readLine(); linha != null; linha = br.readLine()) {
                    sb.append(linha);
                }

                emailJson = sb.toString();

            }

        }

        BDDMockito
            .given(mockedJmsMessage.getBody(String.class))
            .willReturn(emailJson);

        StringBuilder sb = new StringBuilder();

        sb.append("Prezados,")
          .append(lineSeparator())
          .append("Falamos do(a) Empresa Cliente LTDA,")
          .append(lineSeparator())
          .append("Segue em anexo o formulário preenchido para abertura de solicitação de garantia para o equipamento: AVCL486XPTO.")
          .append(lineSeparator())
          .append(lineSeparator())
          .append("Prezado atendente do fornecedor Fornecedor LTDA: ")
          .append(lineSeparator())
          .append(lineSeparator())
          .append("O formulário possui as informações necessárias sobre a solicitação, como, a descrição detalhada do problema,")
          .append(lineSeparator())
          .append("horários disponíveis, como também, os dados de endereço.")
          .append(lineSeparator())
          .append(lineSeparator())
          .append("As solicitações de garantia são realizadas através de um sistema interno de Solicitações de Garantia, com ")
          .append(lineSeparator())
          .append("o objetivo de automatizar os envios de solicitações para diversos fornecedores.")
          .append(lineSeparator())
          .append(lineSeparator())
          .append("Atenciosamente,")
          .append(lineSeparator())
          .append("--")
          .append(lineSeparator())
          .append("Empresa Cliente LTDA - Matriz da Empresa Cliente LTDA")
          .append(lineSeparator())
          .append("FortalCity/Ceará")
          .append(lineSeparator())
          .append("Serviço de Tecnologia da Informação (Empresa Cliente LTDA)")
          .append(lineSeparator())
          .append("(85)3386-4214, (85)3386-4416")
          .append(lineSeparator())
          .append("atendimento.cliente@mail.br, cliente.ltda@mail.br");

        final String emailBodyEsperado = sb.toString();
        final int quantidadeDeAnexosEsperada = 2;

        final int tamanhoDoByteArrayDoFormulario = 13834;
        final int tamanhoDoByteArrayDoVideoDoDefeito = 3208822;

        queueConsumer.onMessage(mockedJmsMessage);

        BDDMockito
            .verify(mockedServicoDeEmail).enviar(emailCaptor.capture(), Mockito.any(Session.class));

        Email email = emailCaptor.getValue();

        Assertions
            .assertEquals("[Ticket#20240101486] Problema Mouse Fornecedor LTDA - Empresa Cliente Inc", email.getAssunto());

        Assertions
            .assertEquals("905f2dd9fbc8bb", email.getRemetente());

        Assertions
            .assertEquals("atendimento.garantia@fornecedor.com, suporte@garantia.com", email.getDestinatario());

        Assertions
            .assertEquals("copia1@email.com, copia2@email.br", email.getCopiaPara());        

        Assertions
            .assertEquals(emailBodyEsperado, email.getCorpoDaMensagem());

        Assertions
            .assertEquals(quantidadeDeAnexosEsperada, email.getAnexos().size());

        Anexo anexoVideo = null;
        Anexo anexoFormulario = null;

        for (Anexo anexo : email.getAnexos()) {

            if (anexo.getNome().equals("VIDEO MONITOR ARTEFATO.mp4")) {

                anexoVideo = anexo;
                continue;
            }

            if (anexo.getNome().equals("Abertura de Chamado AVCL486XPTO 2024 18036123176031463778.docx")) {

                anexoFormulario = anexo;
                break;
            }

        }

        Assertions
            .assertNotNull(anexoVideo);
        Assertions
            .assertNotNull(anexoFormulario);

        Assertions
            .assertEquals(tamanhoDoByteArrayDoFormulario, anexoFormulario.getArquivo().length);
        Assertions
            .assertEquals(tamanhoDoByteArrayDoVideoDoDefeito, anexoVideo.getArquivo().length);

    }

}
