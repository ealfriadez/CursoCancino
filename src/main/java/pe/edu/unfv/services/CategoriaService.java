package pe.edu.unfv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pe.edu.unfv.models.CategoriaModel;
import pe.edu.unfv.repositories.ICategoriaRepository;

@Service
@Primary
public class CategoriaService {

	@Autowired
	private ICategoriaRepository repository;
	
	public List<CategoriaModel> listar(){		
		
		return this.repository.findAll();		
	}
}
