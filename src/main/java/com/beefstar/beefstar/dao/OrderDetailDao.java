package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.infrastructure.entity.OrderDetail;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface OrderDetailDao {
    OrderDetail saveOrder(OrderDetail orderDetail);

    List<OrderDetail> findOrdersByUser(UserInfo currentUser);

    List<OrderDetail> findAllOrders();

    Optional<OrderDetail> findOrderById(Integer orderId);
}
