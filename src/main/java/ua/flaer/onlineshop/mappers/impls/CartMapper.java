package ua.flaer.onlineshop.mappers.impls;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.model.dto.CartDto;
import ua.flaer.onlineshop.model.entities.Cart;
import ua.flaer.onlineshop.model.entities.User;
import ua.flaer.onlineshop.repositories.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;


@Component
public class CartMapper implements Mapper<Cart, CartDto> {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    public CartMapper(ModelMapper modeMapper,
                      UserRepository userRepository){
        this.userRepository = userRepository;
        this.modelMapper = modeMapper;
    }

    @Override
    public CartDto mapTo(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .build();
    }

    @Override
    public Cart mapFrom(CartDto cartDto) {
        System.out.println(cartDto);
        Optional<User> dbUser = userRepository.findById(cartDto.getUserId());

        Cart cart = Cart.builder()
                .id(cartDto.getId())
                .user(dbUser.orElseThrow(
                        () -> new NoSuchElementException(String.format("User with id=%d have not been found in database.",cartDto.getUserId()))
                ))
                .build();

        return cart;

    }
}
