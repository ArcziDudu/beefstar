package com.beefstar.beefstar.infrastructure.jpaRepository;

import com.beefstar.beefstar.infrastructure.entity.Cart;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartJpaRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserInfo(UserInfo userInfo);
}
