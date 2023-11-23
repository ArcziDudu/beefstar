package com.beefstar.beefstar.infrastructure.mapper;

import com.beefstar.beefstar.domain.InvoiceDTO;

import com.beefstar.beefstar.infrastructure.entity.InvoiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceMapper {
    InvoiceDTO mapFromEntity(InvoiceEntity entity);

    InvoiceEntity mapFromDto(InvoiceDTO patientDto);
}
