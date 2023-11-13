package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.OrderDetailDao;
import com.beefstar.beefstar.dao.ProductDao;
import com.beefstar.beefstar.dao.UserInfoDao;
import com.beefstar.beefstar.infrastructure.configuration.JwtRequestFilter;
import com.beefstar.beefstar.infrastructure.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
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


    public OrderDetail placeOrder(OrderInput orderInput){
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();
        OrderDetail orderDetailReturned = null;

        for (OrderProductQuantity o : productQuantityList) {
            Product product = productService.fetchProductDetails(o.getProductId());

            String currentUser = JwtRequestFilter.CURRENT_USER;
            UserInfo userById = userService.findUserById(currentUser);
            OrderDetail orderDetail = OrderDetail.builder()
                    .orderFullName(orderInput.getUserFullName())
                    .orderCode(UUID.randomUUID().toString())
                    .orderContactNumber(orderInput.getUserContactNumber())
                    .orderStatus(ORDER_PLACED)
                    .orderAmount(
                            new BigDecimal(product
                                    .getProductActualPrice()
                                    .toString())
                                    .multiply(new BigDecimal(o.getQuantity()
                                            .toString())))
                    .product(product)
                    .user(userById)
                    .build();
          orderDetailReturned= orderDetailDao.saveOrder(orderDetail);
        }
        return orderDetailReturned;
    }
}
