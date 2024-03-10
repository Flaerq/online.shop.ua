package ua.flaer.onlineshop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.flaer.onlineshop.model.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
}
