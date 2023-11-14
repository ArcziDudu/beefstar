package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.OrderDetailDao;
import com.beefstar.beefstar.domain.OrderInput;
import com.beefstar.beefstar.domain.OrderProductQuantity;
import com.beefstar.beefstar.infrastructure.configuration.security.JwtRequestFilter;
import com.beefstar.beefstar.infrastructure.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderDetailService {
    private final static String ORDER_PLACED = "Placed";
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    public OrderDetail placeOrder(OrderInput orderInput) {
        List<OrderProductQuantity> productQuantityList = orderInput.orderProductQuantityList();
        OrderDetail orderDetailReturned = null;

        for (OrderProductQuantity o : productQuantityList) {
            Product product = productService.fetchProductDetails(o.productId());

            String currentUser = JwtRequestFilter.CURRENT_USER;
            UserInfo userById = userService.findUserById(currentUser);
            OrderDetail orderDetail = OrderDetail.builder()
                    .orderFullName(orderInput.userFullName())
                    .orderCode(UUID.randomUUID().toString())
                    .orderContactNumber(orderInput.userContactNumber())
                    .orderStatus(ORDER_PLACED)
                    .orderAmount(
                            new BigDecimal(product
                                    .getProductActualPrice()
                                    .toString())
                                    .multiply(new BigDecimal(o.quantity()
                                            .toString())))
                    .product(product)
                    .user(userById)
                    .build();
            orderDetailReturned = orderDetailDao.saveOrder(orderDetail);
        }
        return orderDetailReturned;
    }
}
