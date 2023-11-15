package com.beefstar.beefstar.infrastructure.jpaRepository;

import com.beefstar.beefstar.infrastructure.entity.OrderDetail;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailDaoJpaRepository extends JpaRepository< OrderDetail,Integer> {
    List<OrderDetail> findByUser(UserInfo currentUser);

}
