package br.albatross.otrs.view.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named @RequestScoped
public class LogoutBean {

	@Inject
	private FacesContext facesContext;

	public String logout() {
		facesContext.getExternalContext().invalidateSession();
		return facesContext.getViewRoot().getViewId().concat("?faces-redirect=true");
	}

}
