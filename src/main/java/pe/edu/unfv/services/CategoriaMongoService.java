package pe.edu.unfv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.unfv.models.CategoriaMongoModel;
import pe.edu.unfv.repositories.ICategoriaMongoRepository;

@Service
@Primary
public class CategoriaMongoService {

	@Autowired
	private ICategoriaMongoRepository repository;
	
	public List<CategoriaMongoModel> listar(){
		return this.repository.findAll(Sort.by("id").descending());
	}
	
	public boolean buscarPorSlug(String slug){
		if (this.repository.existsBySlug(slug)) {
			return false;
		} else {
			return true;
		}
	}
	
	public void guardar(CategoriaMongoModel categoriaMongoModel) {		
		this.repository.save(categoriaMongoModel);
	}
	
	public CategoriaMongoModel buscarPorId(String id) {
		Optional<CategoriaMongoModel> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public void eliminar(String id) {
		this.repository.deleteById(id);
	}
}
