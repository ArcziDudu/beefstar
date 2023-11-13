package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.infrastructure.entity.OrderDetail;
import com.beefstar.beefstar.infrastructure.entity.OrderInput;
import com.beefstar.beefstar.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {
    private final String NEW_ORDER = "/order/new";
    @Autowired
    private OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('User')")
    @PostMapping(NEW_ORDER)
    public ResponseEntity<OrderDetail> placeOrder(@RequestBody OrderInput orderInput) {
        OrderDetail orderDetail = orderDetailService.placeOrder(orderInput);
        return ResponseEntity.ok(orderDetail);
    }
}
