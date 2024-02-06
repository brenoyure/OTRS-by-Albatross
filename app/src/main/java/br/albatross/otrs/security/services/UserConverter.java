package br.albatross.otrs.security.services;

import br.albatross.otrs.security.models.User;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "userConverter")
public class UserConverter implements Converter<User> {

	@Override
	public User getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.isBlank()) {
			return null;
		}
		User user = new User();
		user.setId(Integer.parseInt(id));
		return user;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, User user) {
		return user == null ? null : String.valueOf(user.getId());
	}

}
