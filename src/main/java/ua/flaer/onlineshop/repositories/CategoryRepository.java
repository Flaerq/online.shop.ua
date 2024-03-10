package ua.flaer.onlineshop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.flaer.onlineshop.model.entities.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
