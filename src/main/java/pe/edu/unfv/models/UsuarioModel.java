package pe.edu.unfv.models;

import java.util.Objects;

public class UsuarioModel {

	private String username;
	
	private String correo;
	
	private String password;	
	
	public UsuarioModel() {
		super();
	}

	public UsuarioModel(String username, String correo, String password) {
		super();
		this.username = username;
		this.correo = correo;
		this.password = password;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(correo, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioModel other = (UsuarioModel) obj;
		return Objects.equals(correo, other.correo) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}	
}
