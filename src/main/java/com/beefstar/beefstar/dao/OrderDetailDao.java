package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.infrastructure.entity.OrderDetail;
import com.beefstar.beefstar.infrastructure.entity.OrderInput;

public interface OrderDetailDao {
    OrderDetail saveOrder(OrderDetail orderDetail);
}
