package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.CartDao;
import com.beefstar.beefstar.infrastructure.configuration.security.JwtAuthenticationFilter;
import com.beefstar.beefstar.infrastructure.entity.Cart;
import com.beefstar.beefstar.infrastructure.entity.Product;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartDao cartDao;
    private final ProductService productService;
    private final UserService userService;


    public Cart addToCart(Integer productId) {
        Product product = productService.fetchProductDetails(productId);
        String username = JwtAuthenticationFilter.CURRENT_USER;
        UserInfo user = null;
        if (username != null) {
            user = userService.findUserById(username);
        }
        List<Cart> cartByUser = cartDao.findCartByUser(user);

        List<Cart> filteredList = cartByUser.stream()
                .filter(c -> Objects.equals(c.getProduct().getProductId(), productId))
                .toList();


        if (!filteredList.isEmpty()) {
            return null;
        }

        if (product != null && user != null) {
            Cart cart = Cart.builder()
                    .product(product)
                    .userInfo(user)
                    .build();

            return cartDao.save(cart);
        }
        return null;
    }

    public List<Cart> getCartDetails() {
        UserInfo user = userService.findUserById(JwtAuthenticationFilter.CURRENT_USER);
        return cartDao.findCartByUser(user);
    }

    public List<Cart> findByUser() {
        UserInfo user = userService.findUserById(JwtAuthenticationFilter.CURRENT_USER);
        return cartDao.findCartByUser(user);
    }

    @Transactional
    public void deleteCart(Cart c) {
        cartDao.deleteCart(c);
    }

    @Transactional
    public void deleteCart(Integer cartId) {
        cartDao.deleteCartById(cartId);
    }
}
