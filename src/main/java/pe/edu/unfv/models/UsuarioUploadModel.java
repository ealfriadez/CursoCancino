package pe.edu.unfv.models;

import java.util.Objects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UsuarioUploadModel {

	@NotEmpty(message = "esta vacio")
	private String username;
	@NotEmpty(message = "esta vacio")
	@Email(message = "ingresado no es valido")
	private String correo;
	@NotEmpty(message = "esta vacio")
	private String password;	
	
	public UsuarioUploadModel(@NotEmpty(message = "esta vacio") String username,
			@NotEmpty(message = "esta vacio") @Email(message = "ingresado no es valido") String correo,
			@NotEmpty(message = "esta vacio") String password, String foto) {
		super();
		this.username = username;
		this.correo = correo;
		this.password = password;
		this.foto = foto;
	}	
	
	public UsuarioUploadModel() {
		super();
	}

	private String foto = "default.png";

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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo, foto, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioUploadModel other = (UsuarioUploadModel) obj;
		return Objects.equals(correo, other.correo) && Objects.equals(foto, other.foto)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "UsuarioUploadModel [username=" + username + ", correo=" + correo + ", password=" + password + ", foto="
				+ foto + "]";
	}	
}
