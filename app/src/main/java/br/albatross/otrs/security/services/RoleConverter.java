package br.albatross.otrs.security.services;

import br.albatross.otrs.security.models.Role;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "rolesConverter")
public class RoleConverter implements Converter<Role> {

	@Override
	public Role getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.isBlank()) {
			return null;
		}
		Role role = new Role();
		role.setId(Integer.parseInt(id));
		return role;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Role role) {
		return role == null ? null : String.valueOf(role.getId());
	}

}
