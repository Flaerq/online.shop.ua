package ua.flaer.onlineshop.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {

    private Long id;

    private Long cartId;

    private Long productId;

    private int quantity;
}
