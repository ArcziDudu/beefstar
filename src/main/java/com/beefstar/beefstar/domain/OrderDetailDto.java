package com.beefstar.beefstar.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderDetailDto(Integer orderId,
                             String orderCode,
                             String orderFullOrder,
                             String orderContactNumber,
                             String orderStatus,
                             BigDecimal orderAmount) {
}
