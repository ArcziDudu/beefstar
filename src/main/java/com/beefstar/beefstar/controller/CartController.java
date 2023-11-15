package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.infrastructure.entity.Cart;
import com.beefstar.beefstar.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    private static final String ADD_TO_CART = "/addToCart/{productId}";
    private static final String GET_CART_DETAILS = "/cartDetails";
    private static final String CART_DELETE = "/cartDelete/{cartItemId}";
    @PreAuthorize("hasRole('User')")
    @PostMapping(ADD_TO_CART)
    public ResponseEntity<Cart> addToCart(@PathVariable(name = "productId") Integer productId){
        return ResponseEntity.ok(cartService.addToCart(productId));
    }

    @GetMapping(GET_CART_DETAILS)
    public ResponseEntity<List<Cart>> getCartDetails(){
        return ResponseEntity.ok(cartService.getCartDetails());
    }

    @DeleteMapping(CART_DELETE)
    public void deleteCart(@PathVariable(name = "cartItemId")Integer cartId){
        cartService.deleteCart(cartId);
    }
}
