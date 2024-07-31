package pe.edu.unfv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.unfv.models.CategoriaModel;

public interface ICategoriaRepository extends JpaRepository<CategoriaModel, Integer>{

	public boolean existsBySlug(String slug);
}
