package br.albatross.otrs.security.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class PasswordService {

	private static final String ALGORITHM = "SHA-512";

	public String generateHashing(String plainTextPassword) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
			byte[] digestedPassword = messageDigest.digest(plainTextPassword.getBytes());
			return Base64.getEncoder().encodeToString(digestedPassword);

		} catch (NoSuchAlgorithmException e) { throw new RuntimeException(e); }

	}

}
