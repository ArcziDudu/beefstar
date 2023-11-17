package com.beefstar.beefstar.infrastructure.JpaImpl;

import com.beefstar.beefstar.dao.OrderDetailDao;
import com.beefstar.beefstar.infrastructure.entity.OrderDetail;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import com.beefstar.beefstar.infrastructure.jpaRepository.OrderDetailDaoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderDetailJpaImpl implements OrderDetailDao {

    private final OrderDetailDaoJpaRepository orderDetailDaoJpaRepository;

    @Override
    public OrderDetail saveOrder(OrderDetail orderDetail) {
        return orderDetailDaoJpaRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> findOrdersByUser(UserInfo currentUser) {
        return orderDetailDaoJpaRepository.findByUser(currentUser);
    }

    @Override
    public List<OrderDetail> findAllOrders() {
        return orderDetailDaoJpaRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findOrderById(Integer orderId) {
        return orderDetailDaoJpaRepository.findById(orderId);
    }

    @Override
    public List<OrderDetail> findByOrderStatus(String status) {
        return orderDetailDaoJpaRepository.findByOrderStatus(status);
    }
}
