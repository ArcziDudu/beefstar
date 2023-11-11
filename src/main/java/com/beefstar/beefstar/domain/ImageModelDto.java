package com.beefstar.beefstar.domain;

import lombok.Builder;

@Builder
public record ImageModelDto(String name,
                            String type,
                            byte[] picByte) {
}
