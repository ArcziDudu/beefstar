package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.infrastructure.entity.Cart;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;

import java.util.List;

public interface CartDao {
    Cart save(Cart cart);
    List<Cart> findCartByUser(UserInfo userInfo);

    void deleteCart(Cart c);

    void deleteCartById(Integer cartId);
}
