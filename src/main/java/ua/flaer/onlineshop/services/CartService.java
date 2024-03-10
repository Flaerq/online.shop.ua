package ua.flaer.onlineshop.services;

import ua.flaer.onlineshop.model.dto.CartDto;

public interface CartService {

    CartDto createCart(CartDto cart);
}
