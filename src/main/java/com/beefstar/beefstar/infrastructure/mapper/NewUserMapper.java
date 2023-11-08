package com.beefstar.beefstar.infrastructure.mapper;

import com.beefstar.beefstar.domain.NewUserDTO;
import com.beefstar.beefstar.infrastructure.entity.NewUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewUserMapper {
    NewUser mapFromDto(NewUserDTO newUser);
}
