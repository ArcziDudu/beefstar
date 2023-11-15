package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.OrderDetailDao;
import com.beefstar.beefstar.domain.OrderInput;
import com.beefstar.beefstar.domain.OrderProductQuantity;
import com.beefstar.beefstar.infrastructure.configuration.security.JwtRequestFilter;
import com.beefstar.beefstar.infrastructure.entity.Cart;
import com.beefstar.beefstar.infrastructure.entity.OrderDetail;
import com.beefstar.beefstar.infrastructure.entity.Product;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    private final static String ORDER_PLACED = "Placed";
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;


    public OrderDetail placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
        List<OrderProductQuantity> productQuantityList = orderInput.orderProductQuantityList();
        OrderDetail orderDetailReturned = null;

        for (OrderProductQuantity o : productQuantityList) {
            Product product = productService.fetchProductDetails(o.productId());

            String currentUser = JwtRequestFilter.CURRENT_USER;
            UserInfo userById = userService.findUserById(currentUser);
            OrderDetail orderDetail = OrderDetail.builder()
                    .orderFullName(orderInput.userFullName())
                    .orderFullAddress(orderInput.userFullAddress())
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
                    .orderDate(OffsetDateTime.now())
                    .build();

            if (!isSingleProductCheckout) {
                List<Cart> cartByUser = cartService.findByUser();
                cartByUser.forEach(c -> cartService.deleteCart(c));
            }
            orderDetailReturned = orderDetailDao.saveOrder(orderDetail);
        }
        return orderDetailReturned;
    }

    public List<OrderDetail> getOrderDetails() {
        String currentUser = JwtRequestFilter.CURRENT_USER;
        UserInfo user = userService.findUserById(currentUser);
        return orderDetailDao.findOrdersByUser(user);

    }

    public List<OrderDetail> getAllOrdersDetails() {
        return orderDetailDao.findAllOrders();
    }

    public OrderDetail markAsDelivered(Integer orderId) {
        Optional<OrderDetail> orderDetailOptional = orderDetailDao.findOrderById(orderId);
        if (orderDetailOptional.isEmpty()) {
            throw new RuntimeException();
        } else {
            OrderDetail orderDetail = orderDetailOptional.get();
            orderDetail.setOrderStatus("Delivered");
            return orderDetailDao.saveOrder(orderDetail);
        }
    }
}
