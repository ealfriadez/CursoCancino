package pe.edu.unfv.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import pe.edu.unfv.models.CategoriaMongoModel;

public interface ICategoriaMongoRepository extends MongoRepository<CategoriaMongoModel, String>{

	public boolean existsBySlug(String slug);
}
