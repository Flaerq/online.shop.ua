package ua.flaer.onlineshop.services;

import ua.flaer.onlineshop.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto createUser(UserDto user);

    List<UserDto> getUsers();

    Optional<UserDto> findUserById(Long id);

    boolean isExist(Long id);

    UserDto fullUpdateById(Long id, UserDto user);

    UserDto partialUpdate(Long id, UserDto user);

    void delete(Long id);
}
