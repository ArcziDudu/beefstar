package com.beefstar.beefstar.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String orderContactNumber;
    private String orderStatus;
    private BigDecimal orderAmount;
    private OffsetDateTime orderDate;
    @OneToOne(cascade = CascadeType.MERGE)
    private Product product;
    @ManyToOne
    private UserInfo user;


}
