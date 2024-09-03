package pe.edu.unfv.models;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
public class ProductosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	@NotEmpty(message = " esta vacio")
	private String nombre;
	
	private String slug;
	@NotEmpty(message = " esta vacio") 
	private String descripcion;
	@NotNull(message = " no puede ser nulo") 
	@Min(5)
	//@Max(5000)
	private int precio;
	
	private String foto;	
	
	@OneToOne
	@JoinColumn(name = "categoria_id")
	private CategoriaModel categoriaId;	
	
	public ProductosModel() {
		super();
	}

	public ProductosModel(int id, @NotEmpty(message = " esta vacio") String nombre, String slug,
			@NotEmpty(message = " esta vacio") String descripcion,
			@NotNull(message = " no puede ser nulo") @Min(5) int precio, String foto, CategoriaModel categoriaId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.slug = slug;
		this.descripcion = descripcion;
		this.precio = precio;
		this.foto = foto;
		this.categoriaId = categoriaId;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public CategoriaModel getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(CategoriaModel categoriaId) {
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
		ProductosModel other = (ProductosModel) obj;
		return Objects.equals(categoriaId, other.categoriaId) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(foto, other.foto) && id == other.id && Objects.equals(nombre, other.nombre)
				&& precio == other.precio && Objects.equals(slug, other.slug);
	}

	@Override
	public String toString() {
		return "ProductosModel [id=" + id + ", nombre=" + nombre + ", slug=" + slug + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", foto=" + foto + ", categoriaId=" + categoriaId + "]";
	}		
}
