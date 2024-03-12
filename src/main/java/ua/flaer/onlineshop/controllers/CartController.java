package ua.flaer.onlineshop.controllers;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.flaer.onlineshop.exceptions.NoCartFoundException;
import ua.flaer.onlineshop.exceptions.NoCartItemFoundInCartException;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.mappers.RequestMapper;
import ua.flaer.onlineshop.model.dto.CartDto;
import ua.flaer.onlineshop.model.dto.CartItemDto;
import ua.flaer.onlineshop.model.dto.CartItemRequestDto;
import ua.flaer.onlineshop.model.entities.Cart;
import ua.flaer.onlineshop.model.entities.CartItem;
import ua.flaer.onlineshop.services.CartService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carts")
@Slf4j
public class CartController {

    private CartService cartService;
    private RequestMapper<CartItemDto, CartItemRequestDto> cartItemRequestDtoMapper;

    public CartController(CartService cartService,
                          RequestMapper<CartItemDto, CartItemRequestDto> cartItemRequestDtoMapper){
        this.cartService = cartService;
        this.cartItemRequestDtoMapper = cartItemRequestDtoMapper;
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

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartDto> addCartItemToCart(@PathVariable("cartId") Long id,
                                                     @RequestBody CartItemRequestDto cartRequestItem){
        try {
            CartDto cart = cartService.updateProductQuantity(id, cartItemRequestDtoMapper.mapFrom(cartRequestItem));

            return ResponseEntity.status(HttpStatus.OK).body(cart);
        } catch (NoCartFoundException ignored){
            log.error("Cart with id="+id+" is not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartDto> removeCartItemFromCart(@PathVariable("cartId") Long cartId,
                                                          @PathVariable("itemId") Long itemId){
        try {
            CartDto cartWithoutCartItem = cartService.removeCartItem(cartId, itemId);
            return ResponseEntity.status(HttpStatus.OK).body(cartWithoutCartItem);
        } catch (NoCartItemFoundInCartException | NoCartFoundException ignored){
            if (ignored instanceof NoCartFoundException){
                log.error("Cart with id="+cartId+" is not found");
            } else {
                log.error("No cart item with id="+itemId+" is not found in cart with id="+cartId);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartDto> updateCartItemQuantity(@PathVariable("cartId") Long cartId,
                                                          @PathVariable("itemId") Long itemId,
                                                          @RequestBody CartItemRequestDto cartItem){
        try{
            CartDto cartWithUpdatedItem = cartService.updateCartItemQuantity(cartId,itemId,cartItemRequestDtoMapper.mapFrom(cartItem));
            return ResponseEntity.status(HttpStatus.OK).body(cartWithUpdatedItem);
        } catch (NoCartItemFoundInCartException | NoCartFoundException ignored){
            if (ignored instanceof NoCartFoundException){
                log.error("Cart with id="+cartId+" is not found");
            } else {
                log.error("No cart item with id="+itemId+" is not found in cart with id="+cartId);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
