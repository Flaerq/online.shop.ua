package ua.flaer.onlineshop.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.flaer.onlineshop.model.entities.CartItem;
import ua.flaer.onlineshop.model.entities.User;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {

    private Long id;

    private Long userId;

    private List<CartItemDto> cartItems;


}
