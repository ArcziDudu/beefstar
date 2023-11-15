package com.beefstar.beefstar.infrastructure.JpaImpl;

import com.beefstar.beefstar.dao.CartDao;
import com.beefstar.beefstar.infrastructure.entity.Cart;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import com.beefstar.beefstar.infrastructure.jpaRepository.CartJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CartJpaImpl implements CartDao {

    @Autowired
    private CartJpaRepository cartJpaRepository;

    @Override
    public Cart save(Cart cart) {
        return cartJpaRepository.save(cart);
    }

    @Override
    public List<Cart> findCartByUser(UserInfo userInfo) {
        return cartJpaRepository.findByUserInfo(userInfo);
    }

    @Override
    public void deleteCart(Cart c) {
         cartJpaRepository.delete(c);
    }

    @Override
    public void deleteCartById(Integer cartId) {
        cartJpaRepository.deleteById(cartId);
    }
}
