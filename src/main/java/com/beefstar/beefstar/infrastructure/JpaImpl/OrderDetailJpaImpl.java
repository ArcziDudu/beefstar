package com.beefstar.beefstar.infrastructure.JpaImpl;

import com.beefstar.beefstar.dao.OrderDetailDao;
import com.beefstar.beefstar.infrastructure.entity.OrderDetail;
import com.beefstar.beefstar.infrastructure.jpaRepository.OrderDetailDaoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailJpaImpl implements OrderDetailDao {
    @Autowired
    private OrderDetailDaoJpaRepository orderDetailDaoJpaRepository;
    @Override
    public OrderDetail saveOrder(OrderDetail orderDetail) {
        return orderDetailDaoJpaRepository.save(orderDetail);
    }
}
