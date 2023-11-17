package com.beefstar.beefstar.infrastructure.mapper;

import com.beefstar.beefstar.domain.ImageModelDto;
import com.beefstar.beefstar.infrastructure.entity.ImageModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImageModelMapper {
    ImageModel mapFromDto(ImageModelDto imageModelDto);

    ImageModelDto mapFromEntity(ImageModel imageModel);
}
