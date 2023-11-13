package com.beefstar.beefstar.infrastructure.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderInput {

    private String userFullName;
    private String userFullAddress;
    private String userContactNumber;
    private List<OrderProductQuantity> orderProductQuantityList;
}
