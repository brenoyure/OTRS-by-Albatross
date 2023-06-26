package br.albatross.otrs.domain.services.garantia;

import static java.io.File.createTempFile;
import static java.time.LocalDate.now;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class FormularioGenerator implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int CURRENT_YEAR = now().getYear();
	private static final String TEMP_FILE_PREFIX = "Abertura de Chamado ";
	private static final String TEMP_FILE_SUFFIX = ".docx";
	private static final String SPACE = " ";

	private static final int STRING_BUILDER_SIZE = 5;

	public File getFormulario(InputStream formTemplate, String numeroDeSerie, String descricaoDoProblema) {
		try (XWPFDocument doc = new XWPFDocument(formTemplate)) {

			var xwpfTable = doc.getTableArray(0);

				xwpfTable.getRow(2).getCell(1).setText(numeroDeSerie);
				xwpfTable.getRow(20).getCell(1).setText(descricaoDoProblema);

			var formularioTempFile = createTempFile(new StringBuilder(STRING_BUILDER_SIZE)
																.append(TEMP_FILE_PREFIX)
																.append(numeroDeSerie)
																.append(SPACE)
																.append(String.valueOf(CURRENT_YEAR))
																.append(SPACE)
																.toString(), TEMP_FILE_SUFFIX);

			try (OutputStream fos = new FileOutputStream(formularioTempFile)) {
				doc.write(fos);
			}

			return formularioTempFile;

		} catch (IOException e) { throw new RuntimeException(e); }

	}

}
