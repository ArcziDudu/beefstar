package com.beefstar.beefstar.domain;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record ProductDTO(Integer productId,
                         String productName,
                         String productDescription,
                         BigDecimal productDiscountedPrice,
                         BigDecimal productActualPrice) {
}
