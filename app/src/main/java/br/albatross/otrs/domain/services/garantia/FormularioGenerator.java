package br.albatross.otrs.domain.services.garantia;

import static java.io.File.createTempFile;
import static java.time.LocalDate.now;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import br.albatross.otrs.domain.models.garantia.apis.cliente.DadosDeEndereco;
import br.albatross.otrs.domain.models.garantia.apis.cliente.HorariosDoCliente;
import br.albatross.otrs.domain.models.garantia.apis.solicitacao.SolicitacaoDeGarantia;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class FormularioGenerator {

	private static final int CURRENT_YEAR = now().getYear();
	private static final String TEMP_FILE_PREFIX = "Abertura de Chamado ";
	private static final String TEMP_FILE_SUFFIX = ".docx";
	private static final String SPACE = " ";

    private static final String ARQUIVOS_FOLDER = "/opt/jboss/wildfly/bin/arquivos";
    private static final String FORMULARIOS_FOLDER = "formularios";
    private static final String FORMULARIO_GARANTIA_DOCX = "Formulario-Garantia.docx";	

	private static final int STRING_BUILDER_SIZE = 5;

	public File getFormulario(SolicitacaoDeGarantia solicitacao) {
		try (XWPFDocument doc = new XWPFDocument(new BufferedInputStream(new FileInputStream(new File(ARQUIVOS_FOLDER + File.separator + FORMULARIOS_FOLDER, FORMULARIO_GARANTIA_DOCX))))) {

            String numeroDeSerie = solicitacao.getNumeroDeSerie();
            String descricaoDoProblema = solicitacao.getDescricaoDoProblema().getDescricaoDetalhada();

			var xwpfTable = doc.getTableArray(0);

				xwpfTable.getRow(2).getCell(1).setText(numeroDeSerie);
				xwpfTable.getRow(3).getCell(1).setText(solicitacao.getDadosDoCliente().getNome() + " - " + solicitacao.getDadosDoCliente().getDescricao());
				xwpfTable.getRow(7).getCell(1).setText(solicitacao.getDadosDoCliente().getNumerosParaContato());

				DadosDeEndereco endereco = solicitacao.getDadosDoCliente().getDadosDeEndereco();

				xwpfTable.getRow(10).getCell(1).setText(endereco.getLogradouro());
				xwpfTable.getRow(11).getCell(1).setText(endereco.getNumero());
				xwpfTable.getRow(13).getCell(1).setText(endereco.getBairro());
				xwpfTable.getRow(14).getCell(1).setText(endereco.getCidade());
				xwpfTable.getRow(15).getCell(1).setText(endereco.getEstado());
				xwpfTable.getRow(17).getCell(1).setText(solicitacao.getDadosDoCliente().getNumerosParaContato());

				HorariosDoCliente horarios = solicitacao.getDadosDoCliente().getHorarios();

				xwpfTable.getRow(18).getCell(1).setText(horarios.getHorarioInicioDoExpediente() + " às " + horarios.getHorarioFimDoExpediente());

				if (horarios.possuiHorarioDeAlmoco()) {
				    xwpfTable.getRow(19).getCell(1).setText(horarios.getInicioDoHorarioDeAlmoco() + " às " + horarios.getFimDoHorarioDeAlmoco());
				}

				xwpfTable.getRow(20).getCell(1).setText(descricaoDoProblema);

			var formularioTempFile = createTempFile(new StringBuilder(STRING_BUILDER_SIZE)
																.append(TEMP_FILE_PREFIX)
																.append(numeroDeSerie)
																.append(SPACE)
																.append(String.valueOf(CURRENT_YEAR))
																.append(SPACE)
																.toString(), TEMP_FILE_SUFFIX);

			try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(formularioTempFile))) {
				doc.write(fos);
			}

			return formularioTempFile;

		} catch (IOException e) { throw new RuntimeException(e); }

	}

}
