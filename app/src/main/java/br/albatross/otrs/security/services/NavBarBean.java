package br.albatross.otrs.security.services;

import jakarta.enterprise.inject.Model;

@Model
public class NavBarBean {

	public String formatUserName(String username) {

		StringBuilder sb = new StringBuilder(username.length());

		sb.insert(0, username.toUpperCase().charAt(0));
		sb.append(username.substring(1));

		return sb.toString();

	}
	
}
