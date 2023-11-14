package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.infrastructure.entity.OrderDetail;

public interface OrderDetailDao {
    OrderDetail saveOrder(OrderDetail orderDetail);
}
