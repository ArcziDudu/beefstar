package com.beefstar.beefstar.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"invoiceId", "uuid"})
@Entity
@Table(name = "invoice_table")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer invoiceId;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "pdf_data", columnDefinition = "BYTEA")
    private byte[] pdfData;
}
