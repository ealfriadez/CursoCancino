package pe.edu.unfv.models;

import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Document(value = "producto")
public class ProductoMongoModel {

	@Id
	private String id; //calculo interno y la fecha del dia
	@NotEmpty(message = "esta vacio")
	private String nombre;
	
	private String slug;
	
	private String descripcion;
	
	private Integer precio;
	
	private String foto;
	
	@DBRef
	private CategoriaMongoModel categoriaId;	

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public CategoriaMongoModel getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(CategoriaMongoModel categoriaId) {
		this.categoriaId = categoriaId;
	}

	public ProductoMongoModel() {
		super();
	}

	public ProductoMongoModel(String id, @NotEmpty(message = "esta vacio") String nombre, String slug,
			String descripcion, Integer precio, String foto, CategoriaMongoModel categoriaId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.slug = slug;
		this.descripcion = descripcion;
		this.precio = precio;
		this.foto = foto;
		this.categoriaId = categoriaId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoriaId, descripcion, foto, id, nombre, precio, slug);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoMongoModel other = (ProductoMongoModel) obj;
		return Objects.equals(categoriaId, other.categoriaId) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(foto, other.foto) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(precio, other.precio)
				&& Objects.equals(slug, other.slug);
	}

	@Override
	public String toString() {
		return "ProductoMongoModel [id=" + id + ", nombre=" + nombre + ", slug=" + slug + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", foto=" + foto + ", categoriaId=" + categoriaId + "]";
	}	
}
