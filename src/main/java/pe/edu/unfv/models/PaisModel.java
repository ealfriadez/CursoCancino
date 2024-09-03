package pe.edu.unfv.models;

import java.util.Objects;

public class PaisModel {

	public Integer id;
	
	public String nombre;	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public PaisModel(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public PaisModel() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaisModel other = (PaisModel) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "PaisModel [id=" + id + ", nombre=" + nombre + "]";
	}
}
