package ua.flaer.onlineshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.flaer.onlineshop.model.dto.CartDto;
import ua.flaer.onlineshop.services.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }


    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cart){
        CartDto savedCart = cartService.createCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCart);
    }
}
