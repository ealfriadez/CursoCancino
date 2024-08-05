package pe.edu.unfv.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import pe.edu.unfv.models.CategoriaMongoModel;
import pe.edu.unfv.models.ProductoMongoModel;

public interface IProductosMongoRepository extends MongoRepository<ProductoMongoModel, String>{

	List<ProductoMongoModel> findAllByCategoriaId(CategoriaMongoModel categoria);
}
