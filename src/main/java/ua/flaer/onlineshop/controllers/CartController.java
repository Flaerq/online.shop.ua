package ua.flaer.onlineshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.flaer.onlineshop.model.dto.CartDto;
import ua.flaer.onlineshop.services.CartService;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<CartDto>> getCarts(){
        List<CartDto> carts = cartService.getCarts();

        if(carts.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(HttpStatus.OK).body(carts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable("id") Long id){
        if (!cartService.isExist(id)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(HttpStatus.OK).body(cartService.findCartById(id).get());
    }




}
