package com.beefstar.beefstar.infrastructure.JpaImpl;


import com.beefstar.beefstar.dao.InvoiceDao;
import com.beefstar.beefstar.domain.InvoiceDTO;
import com.beefstar.beefstar.infrastructure.entity.InvoiceEntity;
import com.beefstar.beefstar.infrastructure.jpaRepository.InvoiceJpaRepository;
import com.beefstar.beefstar.infrastructure.mapper.InvoiceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class InvoiceRepository implements InvoiceDao {
    private InvoiceJpaRepository invoiceJpaRepository;
    private InvoiceMapper invoiceMapper;

    @Override
    public Optional<InvoiceDTO> findByUuid(String uuid) {
        return invoiceJpaRepository.findByUuid(uuid)
                .map(invoiceMapper::mapFromEntity);
    }

    @Override
    public void save(InvoiceEntity pdfDocument) {
        invoiceJpaRepository.save(pdfDocument);
    }


}
