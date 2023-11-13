package com.beefstar.beefstar.infrastructure.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private String orderFullName;
    private String orderCode;
    private String orderFullOrder;
    private String  orderContactNumber;
    private String orderStatus;
    private BigDecimal orderAmount;
    @OneToOne
    private Product product;
    @OneToOne
    private UserInfo user;


}