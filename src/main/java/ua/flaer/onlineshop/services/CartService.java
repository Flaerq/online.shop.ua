package ua.flaer.onlineshop.services;

import ua.flaer.onlineshop.model.dto.CartDto;
import ua.flaer.onlineshop.model.dto.CartItemDto;
import ua.flaer.onlineshop.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface CartService {

    CartDto createCart(CartDto cart);

    List<CartDto> getCarts();

    Optional<CartDto> findCartById(Long id);

    boolean isExist(Long id);

    void delete(Long id);

    CartDto updateProductQuantity(Long cartId, CartItemDto cartItem);

    CartDto removeCartItem(Long cartId, Long itemId);

    CartDto updateCartItemQuantity(Long cartId, Long itemId, CartItemDto cartItem);

}
