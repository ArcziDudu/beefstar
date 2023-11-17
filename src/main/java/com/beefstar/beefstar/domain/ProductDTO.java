package com.beefstar.beefstar.domain;

import com.beefstar.beefstar.infrastructure.entity.ImageModel;
import lombok.With;

import java.math.BigDecimal;
import java.util.Set;


@With
public record ProductDTO(Integer productId,
                         String productName,
                         String productDescription,
                         BigDecimal productDiscountedPrice,
                         BigDecimal productActualPrice,
                         Set<ImageModel> productImages) {
}
