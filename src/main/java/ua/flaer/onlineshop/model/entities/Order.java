package ua.flaer.onlineshop.model.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.flaer.onlineshop.model.enums.DeliveryMethod;
import ua.flaer.onlineshop.model.enums.OrderStatus;
import ua.flaer.onlineshop.model.enums.PaymentMethod;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {

    private Long id;

    private User user;

    private LocalDateTime orderDate;

    private OrderStatus orderStatus;

    private String address;

    private List<Product> productList;

    private Double totalPrice;

    private String comment;

    private PaymentMethod paymentMethod;

    private DeliveryMethod deliveryMethod;

}

