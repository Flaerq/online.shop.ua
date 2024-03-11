package ua.flaer.onlineshop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.flaer.onlineshop.model.entities.CartItem;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
}
