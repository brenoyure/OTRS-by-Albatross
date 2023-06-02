package br.albatross.otrs.view.beans;

import java.io.Serializable;
import java.util.List;

import br.albatross.otrs.domain.models.User;
import br.albatross.otrs.domain.services.UserService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named
@SessionScoped
public class UsersBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private User user = new User();

	@Inject
	private UserService service;

	private List<User> users;

	public List<User> getUsers() {
		if (users == null) {
			return users = service.listarTodos();
		}
		return users;
	}
	
	
	
}
