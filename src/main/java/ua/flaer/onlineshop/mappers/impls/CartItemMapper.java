package ua.flaer.onlineshop.mappers.impls;

import org.springframework.stereotype.Component;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.model.dto.CartItemDto;
import ua.flaer.onlineshop.model.entities.CartItem;
import ua.flaer.onlineshop.repositories.CartRepository;
import ua.flaer.onlineshop.repositories.ProductRepository;


@Component
public class CartItemMapper implements Mapper<CartItem, CartItemDto> {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public CartItemMapper(CartRepository cartRepository,
                          ProductRepository productRepository){
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartItemDto mapTo(CartItem cartItem) {
        return CartItemDto.builder()
                .id(cartItem.getId())
                .cartId(cartItem.getCart().getId())
                .productId(cartItem.getProduct().getId())
                .quantity(cartItem.getQuantity())
                .build();
    }

    @Override
    public CartItem mapFrom(CartItemDto cartItemDto) {
        return CartItem.builder()
                .id(cartItemDto.getId())
                .cart(cartRepository.findById(cartItemDto.getCartId()).get())
                .product(productRepository.findById(cartItemDto.getProductId()).get())
                .build();
    }
}
