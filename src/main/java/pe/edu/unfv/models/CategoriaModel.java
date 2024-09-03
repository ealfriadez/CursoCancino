package pe.edu.unfv.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "categoria")
public class CategoriaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nombre;
	
	private String slug;	
	
	public CategoriaModel() {
		super();
	}

	public CategoriaModel(int id, String nombre, String slug) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.slug = slug;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		CategoriaModel other = (CategoriaModel) obj;
		return id == other.id && Objects.equals(nombre, other.nombre) && Objects.equals(slug, other.slug);
	}

	@Override
	public String toString() {
		return "CategoriaModel [id=" + id + ", nombre=" + nombre + ", slug=" + slug + "]";
	}	
}
