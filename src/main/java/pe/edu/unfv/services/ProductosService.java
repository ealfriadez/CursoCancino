package pe.edu.unfv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.unfv.models.CategoriaModel;
import pe.edu.unfv.models.ProductosModel;
import pe.edu.unfv.repositories.IProductosRepository;

@Service
@Primary
public class ProductosService {

	@Autowired
	private IProductosRepository repository;
	
	public List<ProductosModel> listar_respaldo(){		
		
		return this.repository.findAll();		
	}
	
	public List<ProductosModel> listar(){		
		
		return this.repository.findAll(Sort.by("id").descending());
	}
	
	public List<ProductosModel> listar_por_categorias(CategoriaModel categoria){		
		
		return this.repository.findAllByCategoriaId(categoria);
	}
	
	public void guardar(ProductosModel producto) {
		this.repository.save(producto);
	}
	
	public ProductosModel buscarPorId(Integer id) {
		Optional<ProductosModel> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public void eliminar(Integer id) {
		this.repository.deleteById(id);
	}
}
