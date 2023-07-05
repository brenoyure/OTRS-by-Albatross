package br.albatross.otrs.domain.services.garantia;

import static java.io.File.separator;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class FormularioInputStreamGenerator {

	private static final String ARQUIVOS_FOLDER = "arquivos";
	private static final String FORMULARIOS_FOLDER = "formularios";
	private static final String FORMULARIO_GARANTIA_DOCX = "Formulario Garantia.docx";

	public InputStream getInputStream() {
		try { return new BufferedInputStream(new FileInputStream(new File(ARQUIVOS_FOLDER + separator + FORMULARIOS_FOLDER, FORMULARIO_GARANTIA_DOCX))); } 

		catch (FileNotFoundException e) { throw new RuntimeException(e); }

	}
}
