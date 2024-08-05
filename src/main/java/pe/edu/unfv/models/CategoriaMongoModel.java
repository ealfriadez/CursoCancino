package pe.edu.unfv.models;

import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Document(value = "categoria")
public class CategoriaMongoModel {
	
	@Id
	private String id; //calculo interno y la fecha del dia
	@NotEmpty(message = "esta vacio")
	private String nombre;
	
	private String slug;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public CategoriaMongoModel(String id, @NotEmpty(message = "esta vacio") String nombre, String slug) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.slug = slug;
	}

	public CategoriaMongoModel() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, slug);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaMongoModel other = (CategoriaMongoModel) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre) && Objects.equals(slug, other.slug);
	}

	@Override
	public String toString() {
		return "CategoriaMongoModel [id=" + id + ", nombre=" + nombre + ", slug=" + slug + "]";
	}
}
