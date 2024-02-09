package br.albatross.otrs.domain.services.garantia;

import static java.io.File.createTempFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.Part;

@RequestScoped
public class AnexoGenerator {

	public File getAnexo(Part uploadedFile) {
		try {
			String fileName       =  uploadedFile.getSubmittedFileName();
		    String fileExtension  =  getFileExtension(fileName);

			File anexoTempFile    =  createTempFile(fileName.replace(fileExtension, ""), fileExtension);

			try (OutputStream os = new BufferedOutputStream(new FileOutputStream(anexoTempFile))) {
				uploadedFile.getInputStream().transferTo(os);
			}

			return anexoTempFile;

		} catch (IOException e) { throw new RuntimeException(e); }

	}

	public File getAnexo(String outputFileName, byte[] byteArray) {

		try (InputStream bufferedByteArrayInputStream = new BufferedInputStream(new ByteArrayInputStream(byteArray))) {

		    String fileExtension  =  getFileExtension(outputFileName);
			File anexoTempFile    =  createTempFile(outputFileName.replace(fileExtension, ""), fileExtension);
			
			try (OutputStream os = new BufferedOutputStream(new FileOutputStream(anexoTempFile))) {
				bufferedByteArrayInputStream.transferTo(os);
			}

			return anexoTempFile;


		} catch (IOException e) { throw new RuntimeException(e); }


	}

	private String getFileExtension(String fileName) {
		return fileName.substring((fileName.length() - 4));
	}

}
