package com.beefstar.beefstar.domain;

import com.beefstar.beefstar.infrastructure.entity.ImageModel;
import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.util.Set;


@With
@Builder
public record ProductDTO(Integer productId,
                         String productName,
                         String productDescription,
                         String productCategory,
                         BigDecimal productActualPrice,
                         Set<ImageModel> productImages) {
}
