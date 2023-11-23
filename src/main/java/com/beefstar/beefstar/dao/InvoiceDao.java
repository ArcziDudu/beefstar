package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.domain.InvoiceDTO;
import com.beefstar.beefstar.infrastructure.entity.InvoiceEntity;

import java.util.Optional;

public interface InvoiceDao {
    Optional<InvoiceDTO> findByUuid(String uuid);

    void save(InvoiceEntity pdfDocument);

}
