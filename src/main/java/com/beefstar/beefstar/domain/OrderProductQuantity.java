package com.beefstar.beefstar.domain;

import lombok.Builder;

@Builder
public record OrderProductQuantity(Integer productId,
                                   Integer quantity) {
}
