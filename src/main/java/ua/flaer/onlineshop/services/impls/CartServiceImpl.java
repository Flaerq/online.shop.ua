package ua.flaer.onlineshop.services.impls;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.flaer.onlineshop.exceptions.NoCartFoundException;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.mappers.impls.CartMapper;
import ua.flaer.onlineshop.model.dto.CartDto;
import ua.flaer.onlineshop.model.dto.CartItemDto;
import ua.flaer.onlineshop.model.entities.Cart;
import ua.flaer.onlineshop.model.entities.CartItem;
import ua.flaer.onlineshop.repositories.CartItemRepository;
import ua.flaer.onlineshop.repositories.CartRepository;
import ua.flaer.onlineshop.services.CartService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private Mapper<Cart, CartDto> cartMapper;
    private Mapper<CartItem, CartItemDto> cartItemMapper;

    public CartServiceImpl(CartRepository cartRepository,
                           Mapper<Cart, CartDto> cartMapper,
                           CartItemRepository cartItemRepository,
                           Mapper<CartItem, CartItemDto> cartItemMapper){
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public CartDto createCart(CartDto cart) {
        Cart savedCart = cartRepository.save(cartMapper.mapFrom(cart));
        return cartMapper.mapTo(savedCart);
    }

    @Override
    public List<CartDto> getCarts() {
        return StreamSupport.stream(cartRepository.findAll().spliterator(), false)
                .map(cartMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CartDto> findCartById(Long id) {
        return cartRepository.findById(id)
                .map(cartMapper::mapTo);
    }

    @Override
    public boolean isExist(Long id) {
        return cartRepository.existsById(id);
    }



    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public CartDto updateProductQuantity(Long cartId, CartItemDto cartItem) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new NoCartFoundException(cartId));

        List<CartItem> cartItems = cart.getCartItems();

        Optional<CartItem> cartItemWithSimilarProductId = cartItems.stream()
                .filter(item -> item.getProduct().getId() == cartItem.getProductId())
                .findAny();
        if (cartItemWithSimilarProductId.isPresent()){
            CartItem existingCartItem = cartItemWithSimilarProductId.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem savedCartItem = cartItemRepository.save(cartItemMapper.mapFrom(cartItem));
            cart.getCartItems().add(savedCartItem);
        }

        return cartMapper.mapTo(cart);
    }

}
