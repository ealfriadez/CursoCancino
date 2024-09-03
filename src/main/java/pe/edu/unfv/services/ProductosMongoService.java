package pe.edu.unfv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.unfv.models.CategoriaMongoModel;
import pe.edu.unfv.models.ProductoMongoModel;
import pe.edu.unfv.repositories.IProductosMongoRepository;

@Service
@Primary
public class ProductosMongoService {

	@Autowired
	private IProductosMongoRepository repository;
	
	public List<ProductoMongoModel> listar(){
		return this.repository.findAll(Sort.by("id").descending());
	}
	
	public void guardar(ProductoMongoModel producto) {
		this.repository.save(producto);
	}
	
	public ProductoMongoModel buscarPorId(String id) {
		Optional<ProductoMongoModel> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public void eliminar(String id) {
		this.repository.deleteById(id);
	}	

	public List<ProductoMongoModel> listar_por_categorias(CategoriaMongoModel categoria){		
		return this.repository.findAllByCategoriaId(categoria);
	}
	
	public Page<ProductoMongoModel> listar_paginacion(Pageable pageable){		
		
		return this.repository.findAll(pageable);
	}
}
