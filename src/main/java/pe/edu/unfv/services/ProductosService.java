package pe.edu.unfv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pe.edu.unfv.models.ProductosModel;
import pe.edu.unfv.repositories.IProductosRepository;

@Service
@Primary
public class ProductosService {

	@Autowired
	private IProductosRepository repository;
	
	public List<ProductosModel> listar(){		
		
		return this.repository.findAll();		
	}
}
