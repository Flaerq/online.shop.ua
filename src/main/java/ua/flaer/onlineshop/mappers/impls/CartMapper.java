package ua.flaer.onlineshop.mappers.impls;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.model.dto.CartDto;
import ua.flaer.onlineshop.model.dto.CartItemDto;
import ua.flaer.onlineshop.model.entities.Cart;
import ua.flaer.onlineshop.model.entities.CartItem;
import ua.flaer.onlineshop.model.entities.User;
import ua.flaer.onlineshop.repositories.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class CartMapper implements Mapper<Cart, CartDto> {

    private ModelMapper modelMapper;
    private Mapper<CartItem, CartItemDto> cartItemMapper;
    private UserRepository userRepository;

    public CartMapper(ModelMapper modeMapper,
                      UserRepository userRepository,
                      Mapper<CartItem, CartItemDto> cartItemMapper){
        this.userRepository = userRepository;
        this.modelMapper = modeMapper;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public CartDto mapTo(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .cartItems(cart.getCartItems().stream()
                        .map(cartItemMapper::mapTo)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Cart mapFrom(CartDto cartDto) {
        Optional<User> dbUser = userRepository.findById(cartDto.getUserId());

        Cart cart = Cart.builder()
                .id(cartDto.getId())
                .user(dbUser.orElseThrow(
                        () -> new NoSuchElementException(String.format("User with id=%d have not been found in database.",cartDto.getUserId()))
                ))
                .cartItems(cartDto.getCartItems().stream()
                        .map(cartItemMapper::mapFrom)
                        .collect(Collectors.toList()))
                .build();

        return cart;

    }
}
