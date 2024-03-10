package ua.flaer.onlineshop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.flaer.onlineshop.model.entities.Product;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
}
