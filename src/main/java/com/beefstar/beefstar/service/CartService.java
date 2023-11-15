package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.CartDao;
import com.beefstar.beefstar.infrastructure.configuration.security.JwtRequestFilter;
import com.beefstar.beefstar.infrastructure.entity.Cart;
import com.beefstar.beefstar.infrastructure.entity.Product;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CartService {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;
    @Transactional
    public Cart addToCart(Integer productId){
        Product product = productService.fetchProductDetails(productId);
        String username = JwtRequestFilter.CURRENT_USER;
        UserInfo user = null;
        if(username!=null){
           user = userService.findUserById(username);
        }
        List<Cart> cartByUser = cartDao.findCartByUser(user);

        List<Cart> filteredList = cartByUser.stream()
                .filter(c -> Objects.equals(c.getProduct().getProductId(), productId))
                .toList();

        if(!filteredList.isEmpty()){
            return null;
        }
        if(product != null && user != null){
            Cart cart = Cart.builder()
                    .userInfo(user)
                    .product(product)
                    .build();
            return cartDao.save(cart);
        }
        return null;
    }

    public List<Cart> getCartDetails(){
        UserInfo user = userService.findUserById(JwtRequestFilter.CURRENT_USER);
        return cartDao.findCartByUser(user);
    }

    public List<Cart> findByUser() {
        UserInfo user = userService.findUserById(JwtRequestFilter.CURRENT_USER);
        return cartDao.findCartByUser(user);
    }

    public void deleteCart(Cart c) {
        System.out.println("hej");
         cartDao.deleteCart(c);
    }

    public void deleteCart(Integer cartId) {
        System.out.println("hej");
        cartDao.deleteCartById(cartId);
    }
}
