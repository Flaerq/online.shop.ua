package ua.flaer.onlineshop.mappers.impls;

import org.springframework.stereotype.Component;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.mappers.RequestMapper;
import ua.flaer.onlineshop.model.dto.CartItemDto;
import ua.flaer.onlineshop.model.dto.CartItemRequestDto;
import ua.flaer.onlineshop.model.entities.CartItem;


@Component
public class CartItemRequestMapper implements RequestMapper<CartItemDto, CartItemRequestDto> {

    @Override
    public CartItemDto mapFrom(CartItemRequestDto cartItemRequestDto) {
        return CartItemDto.builder()
                .productId(cartItemRequestDto.getProductId())
                .quantity(cartItemRequestDto.getQuantity())
                .build();
    }
}
