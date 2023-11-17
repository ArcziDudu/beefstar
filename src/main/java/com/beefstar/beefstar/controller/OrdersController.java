package com.beefstar.beefstar.controller;

import com.beefstar.beefstar.domain.OrderInput;
import com.beefstar.beefstar.infrastructure.entity.OrderDetail;
import com.beefstar.beefstar.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController {
    private final String NEW_ORDER = "/order/new/{isSingleProductCheckout}";
    private final String ORDER_DETAILS = "/order/details";
    private final String ALL_ORDER_DETAILS = "/order/details/all/{status}";
    private final String ORDER_STATUS = "/order/markAsDelivered/{orderId}";
    private final OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('User')")
    @PostMapping(NEW_ORDER)
    public ResponseEntity<OrderDetail> placeOrder(
            @PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
            @RequestBody
            OrderInput orderInput) {
        OrderDetail orderDetail = orderDetailService.placeOrder(orderInput, isSingleProductCheckout);
        return ResponseEntity.ok(orderDetail);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping(ORDER_DETAILS)
    public ResponseEntity<List<OrderDetail>> getOrderDetails() {
        return ResponseEntity.ok(orderDetailService.getOrderDetails());
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping(ALL_ORDER_DETAILS)
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails(@PathVariable(name = "status") String status) {
        return ResponseEntity.ok(orderDetailService.getAllOrdersDetails(status));
    }

    @PreAuthorize("hasRole('Admin')")
    @PatchMapping(ORDER_STATUS)
    public ResponseEntity<OrderDetail> markOrderAsDelivered(@PathVariable(name = "orderId") Integer orderId) {
        return ResponseEntity.ok(orderDetailService.markAsDelivered(orderId));
    }


}
