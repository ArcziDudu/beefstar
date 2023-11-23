package com.beefstar.beefstar.domain;

import lombok.Builder;
import lombok.Getter;

@Builder

public record InvoiceDTO(Integer invoiceId,

                         String uuid,
                         byte[] pdfData) {
}
