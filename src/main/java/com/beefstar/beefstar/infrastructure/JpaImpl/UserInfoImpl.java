package com.beefstar.beefstar.infrastructure.JpaImpl;

import com.beefstar.beefstar.dao.UserInfoDao;
import com.beefstar.beefstar.domain.UserInfoDTO;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import com.beefstar.beefstar.infrastructure.jpaRepository.NewUserJpaRepository;
import com.beefstar.beefstar.infrastructure.mapper.NewUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserInfoImpl implements UserInfoDao {
    private NewUserJpaRepository newUserJpaRepository;
    private NewUserMapper newUserMapper;


    @Override
    public UserInfo save(UserInfoDTO newUser) {
        return newUserJpaRepository.save(newUserMapper.mapFromDto(newUser));
    }

    @Override
    public Optional<UserInfo> findById(String username) {
        return newUserJpaRepository.findById(username);
    }
}
