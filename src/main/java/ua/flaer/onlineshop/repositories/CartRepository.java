package ua.flaer.onlineshop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.flaer.onlineshop.model.entities.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long>{
}
