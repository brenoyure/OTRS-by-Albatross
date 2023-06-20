package br.albatross.otrs.view.beans;

import static java.time.LocalDate.now;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import jakarta.ejb.Singleton;

@Singleton
public class FormularioGenerator implements Serializable {

	private static final long serialVersionUID = 1L;

	public File getFormulario(InputStream formTemplate, String numeroDeSerie, String descricaoDoProblema) {
		try (XWPFDocument doc = new XWPFDocument(formTemplate)) {

			List<XWPFTable> xwpfTables = doc.getTables();

			for(XWPFTable xwpfTable : xwpfTables) {
				xwpfTable.getRow(2).getCell(1).setText(numeroDeSerie);
				xwpfTable.getRow(20).getCell(1).setText(descricaoDoProblema);
			}

			try (FileOutputStream fos = new FileOutputStream("/tmp/Abertura_de_Chamado_" + numeroDeSerie + "_" + now().getYear() + ".docx")) {
				doc.write(fos);
			}

			return new File("/tmp/Abertura_de_Chamado_" + numeroDeSerie + "_2023.docx");

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
