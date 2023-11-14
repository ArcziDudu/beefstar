package com.beefstar.beefstar.domain;


import java.util.List;

public record OrderInput(String userFullName,
                         String userFullAddress,
                         String userContactNumber,
                         List<OrderProductQuantity> orderProductQuantityList) {
}
