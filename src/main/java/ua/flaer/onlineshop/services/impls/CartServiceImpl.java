package ua.flaer.onlineshop.services.impls;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.mappers.impls.CartMapper;
import ua.flaer.onlineshop.model.dto.CartDto;
import ua.flaer.onlineshop.model.entities.Cart;
import ua.flaer.onlineshop.repositories.CartRepository;
import ua.flaer.onlineshop.services.CartService;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private Mapper<Cart, CartDto> cartMapper;

    public CartServiceImpl(CartRepository cartRepository,
            Mapper<Cart, CartDto> cartMapper){
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public CartDto createCart(CartDto cart) {
        Cart savedCart = cartRepository.save(cartMapper.mapFrom(cart));
        return cartMapper.mapTo(savedCart);
    }
}
