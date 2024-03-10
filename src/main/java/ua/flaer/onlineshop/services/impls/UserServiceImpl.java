package ua.flaer.onlineshop.services.impls;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.mappers.impls.UserMapper;
import ua.flaer.onlineshop.model.dto.UserDto;
import ua.flaer.onlineshop.model.entities.User;
import ua.flaer.onlineshop.repositories.UserRepository;
import ua.flaer.onlineshop.services.UserService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private Mapper<User, UserDto> userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           Mapper<User, UserDto> userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserDto user) {
        User savedUser = userRepository.save(userMapper.mapFrom(user));
        return userMapper.mapTo(savedUser);
    }

    @Override
    public List<UserDto> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::mapTo);
    }

    @Override
    public boolean isExist(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public UserDto fullUpdateById(Long id, UserDto user) {
        user.setId(id);

        User updatedUser = userRepository.save(userMapper.mapFrom(user));

        return userMapper.mapTo(updatedUser);
    }

    @Override
    public UserDto partialUpdate(Long id, UserDto user) {
        user.setId(id);

        User updatedUser = userRepository.findById(id)
                .map((bdUser) -> {
                    Optional.ofNullable(user.getFirstName()).ifPresent(bdUser::setFirstName);
                    Optional.ofNullable(user.getLastName()).ifPresent(bdUser::setLastName);
                    Optional.ofNullable(user.getEmail()).ifPresent(bdUser::setEmail);
                    Optional.ofNullable(user.getPhoneNumber()).ifPresent(bdUser::setPhoneNumber);
                    Optional.ofNullable(user.getPassword()).ifPresent(bdUser::setPassword);
                    Optional.ofNullable(user.getAddress()).ifPresent(bdUser::setAddress);
                    Optional.ofNullable(user.getUserType()).ifPresent(bdUser::setUserType);
                    return bdUser;
                }).get();

        userRepository.save(updatedUser);

        return userMapper.mapTo(updatedUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
