package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.OrderDetailDao;
import com.beefstar.beefstar.domain.OrderInput;
import com.beefstar.beefstar.domain.OrderProductQuantity;
import com.beefstar.beefstar.infrastructure.configuration.security.JwtAuthenticationFilter;
import com.beefstar.beefstar.infrastructure.entity.Cart;
import com.beefstar.beefstar.infrastructure.entity.OrderDetail;
import com.beefstar.beefstar.infrastructure.entity.Product;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@CacheConfig(cacheNames = "orders")
@RequiredArgsConstructor
public class OrderDetailService {
    private final static String ORDER_PLACED = "Placed";

    private final OrderDetailDao orderDetailDao;


    private final ProductService productService;


    private final UserService userService;

    private final CartService cartService;
    private final InvoiceGeneratorService invoiceGeneratorService;

    @Transactional
    @CacheEvict(value = "orders", allEntries = true)
    public OrderDetail placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
        List<OrderProductQuantity> productQuantityList = orderInput.orderProductQuantityList();
        OrderDetail orderDetailReturned = null;

        for (OrderProductQuantity o : productQuantityList) {
            Product product = productService.fetchProductDetails(o.productId());

            String currentUser = JwtAuthenticationFilter.CURRENT_USER;
            UserInfo userById = userService.findUserById(currentUser);
            OrderDetail orderDetail = OrderDetail.builder()
                    .orderFullName(orderInput.userFullName())
                    .orderFullAddress(orderInput.userFullAddress())
                    .orderContactNumber(orderInput.userContactNumber())
                    .orderStatus(ORDER_PLACED)
                    .orderAmount(
                            new BigDecimal(product
                                    .getProductActualPrice())
                                    .multiply(new BigDecimal(o.quantity()
                                            .toString())))
                    .product(product)
                    .user(userById)
                    .orderDate(OffsetDateTime.now())
                    .build();

            if (!isSingleProductCheckout) {
                List<Cart> cartByUser = cartService.findByUser();
                cartByUser.forEach(cartService::deleteCart);
            }
            orderDetailReturned = orderDetailDao.saveOrder(orderDetail);
        }
        return orderDetailReturned;
    }

    @Cacheable("orders")
    @CacheEvict(value = "orders", allEntries = true)
    public List<OrderDetail> getOrderDetails() {
        String currentUser = JwtAuthenticationFilter.CURRENT_USER;
        UserInfo user = userService.findUserById(currentUser);
        return orderDetailDao.findOrdersByUser(user);

    }

    @Cacheable("orders")
    public List<OrderDetail> getAllOrdersDetails(String status) {

        if (status.equalsIgnoreCase("all")) {
            return orderDetailDao.findAllOrders();
        } else {
            return orderDetailDao.findByOrderStatus(status);
        }

    }

    @Transactional
    @CacheEvict(value = "orders", allEntries = true)
    public OrderDetail markAsDelivered(Integer orderId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);
        Optional<OrderDetail> orderDetailOptional = orderDetailDao.findOrderById(orderId);
        if (orderDetailOptional.isEmpty()) {
            throw new RuntimeException();
        } else {
            OrderDetail orderDetail = orderDetailOptional.get();
            orderDetail.setOrderStatus("Delivered");
            orderDetail.setUuid(UUID.randomUUID().toString());
            orderDetail.setInvoiceAvailable(true);

            invoiceGeneratorService.generatePdfWithRetry("<!doctype html><html><head><meta charset=utf-8><style>.invoice-box{max-width:800px;padding:30px;border:1px solid #eee;box-shadow:0 0 10px rgba(0,0,0,.15);font-size:16px;line-height:24px;font-family:'Helvetica Neue','Helvetica',Helvetica,Arial,sans-serif;color:#555;}.invoice-box table{width:100%;line-height:inherit;text-align:left;}.invoice-box table td{padding:5px;vertical-align:top;}.invoice-box table tr td:nth-child(2){text-align:right;}.invoice-box table tr.top table td{padding-bottom:20px;}.invoice-box table tr.top table td.title{font-size:45px;line-height:45px;color:#333;}.invoice-box table tr.information table td{padding-bottom:40px;}.invoice-box table tr.heading td{background:#eee;border-bottom:1px solid #ddd;font-weight:bold;}.invoice-box table tr.details td{padding-bottom:20px;}.invoice-box table tr.item td{border-bottom:1px solid #eee;}.invoice-box table tr.item.last td{border-bottom:none;}.invoice-box table tr.total td:nth-child(2){border-top:2px solid #eee;font-weight:bold;}@media only screen and (max-width:600px){.invoice-box table tr.top table td{width:100%;display:block;text-align:center;}.invoice-box table tr.information table td{width:100%;display:block;text-align:center;}}/** RTL **/.rtl{direction:rtl;font-family:Tahoma,'Helvetica Neue','Helvetica',Helvetica,Arial,sans-serif;}.rtl table{text-align:right;}.rtl table tr td:nth-child(2){text-align:left;}</style></head><body><div class=invoice-box><table cellpadding=0 cellspacing=0><tr class=top><td colspan=2><table><tr><td class=title><h6 style=color:#320e0e>Beefstar</h6></td><td>Invoice number: "
                            + orderDetail.getUuid()
                            +"<br>Date of shopping: "+ orderDetail.getOrderDate().format(formatter)
                            +"<br>Name of marketplace: Beefstar general partnership"
                            +"</td>" +
                            "</tr></table></td></tr><tr class=heading>" +
                            "<td>Title</td><td>Shopping at Beefstar marketplace</td>" +
                            "</tr><tr class=heading><td>Product</td><td>" + orderDetail.getProduct().getProductName()
                            + "</td></tr><tr class=item><td>Category</td><td>" + orderDetail.getProduct().getProductCategory()
                            + "</td></tr><tr class=item><td>Buyer</td><td>"
                            + orderDetail.getOrderFullName()+"</td></tr>"
                            + "</td></tr><tr class=item><td>Buyer address</td><td>"  + orderDetail.getOrderFullAddress()+
                            "<tr class=item><td></td><td></td></tr>" +
                            "<td>Payment method</td><td>Bank transfer</td><tr class=total><td></td>" +
                            "<td>Cost: " + orderDetail.getOrderAmount()+
                            " USD.</td></tr><tr class=item><td>Date of issue of the invoice</td><td>" + OffsetDateTime.now().format(formatter)
                            +"</table></div></body></html>"
                    , orderDetail.getUuid());
            return orderDetailDao.saveOrder(orderDetail);
        }
    }

}
