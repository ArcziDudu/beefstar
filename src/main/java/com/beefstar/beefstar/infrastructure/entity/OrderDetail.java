package com.beefstar.beefstar.infrastructure.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

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
    private String orderFullAddress;
    private String  orderContactNumber;
    private String orderStatus;
    private BigDecimal orderAmount;
    private OffsetDateTime orderDate;
    @OneToOne
    private Product product;
    @OneToOne
    private UserInfo user;


}
