package com.beefstar.beefstar.domain;


import lombok.Builder;
import lombok.With;

import java.util.List;
@Builder
@With
public record OrderInput(String userFullName,
                         String userFullAddress,
                         String userContactNumber,
                         List<OrderProductQuantity> orderProductQuantityList) {
}
