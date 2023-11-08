package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.domain.UserInfoDTO;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;

import java.util.Optional;

public interface UserInfoDao {
    UserInfo save(UserInfoDTO newUser);

    Optional<UserInfo> findById(String username);
}
