package pe.edu.unfv.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import pe.edu.unfv.models.CategoriaModel;
import pe.edu.unfv.repositories.ICategoriaRepository;

@Service
@Primary
public class CategoriaService {

	@Autowired
	private ICategoriaRepository repository;
	
	public List<CategoriaModel> listar(){		
		
		return this.repository.findAll(Sort.by("id").descending());		
	}
	
	public List<CategoriaModel> listar_respaldo(){		
		
		return this.repository.findAll();		
	}
	
	
	public void guardar(CategoriaModel categoriaModel) {
		
		this.repository.save(categoriaModel);
	}
	
	public CategoriaModel buscarPorId(Integer id) {
		Optional<CategoriaModel> optional = this.repository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public boolean buscarPorSlug(String slug){
		if (this.repository.existsBySlug(slug)) {
			return false;
		} else {
			return true;
		}
	}
	
	public void eliminar(Integer id) {
		this.repository.deleteById(id);
	}
}
