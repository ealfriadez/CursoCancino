package pe.edu.unfv.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.unfv.models.CategoriaModel;
import pe.edu.unfv.models.ProductosModel;

public interface IProductosRepository extends JpaRepository<ProductosModel, Integer>{

	List<ProductosModel> findAllByCategoriaId(CategoriaModel categoria);
	
	List<ProductosModel> findAllByCategoriaIdIn(List<CategoriaModel> categorias);
}
