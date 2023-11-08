package com.beefstar.beefstar.infrastructure.mapper;

import com.beefstar.beefstar.domain.UserInfoDTO;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NewUserMapper {
    UserInfo mapFromDto(UserInfoDTO newUser);
}
