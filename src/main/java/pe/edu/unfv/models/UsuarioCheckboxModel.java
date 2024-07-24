package pe.edu.unfv.models;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UsuarioCheckboxModel {

	@NotEmpty(message = "esta vacio")
	private String username;
	@NotEmpty(message = "esta vacio")
	@Email(message = "ingresado no es valido")
	private String correo;
	@NotEmpty(message = "esta vacio")
	private String password;
	
	private List<InteresesModel> interesesModels;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<InteresesModel> getInteresesModels() {
		return interesesModels;
	}

	public void setInteresesModels(List<InteresesModel> interesesModels) {
		this.interesesModels = interesesModels;
	}
	
	
}
