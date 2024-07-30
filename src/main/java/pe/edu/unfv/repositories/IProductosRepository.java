package pe.edu.unfv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.unfv.models.ProductosModel;

public interface IProductosRepository extends JpaRepository<ProductosModel, Integer>{

}
