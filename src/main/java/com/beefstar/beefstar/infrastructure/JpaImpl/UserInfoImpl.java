package com.beefstar.beefstar.infrastructure.JpaImpl;

import com.beefstar.beefstar.dao.UserInfoDao;
import com.beefstar.beefstar.domain.UserInfoDTO;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import com.beefstar.beefstar.infrastructure.jpaRepository.NewUserJpaRepository;
import com.beefstar.beefstar.infrastructure.mapper.NewUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserInfoImpl implements UserInfoDao {
    private final NewUserJpaRepository newUserJpaRepository;
    private final NewUserMapper newUserMapper;


    @Override
    public UserInfo save(UserInfoDTO newUser) {
        return newUserJpaRepository.save(newUserMapper.mapFromDto(newUser));
    }

    @Override
    public Optional<UserInfo> findById(String username) {
        return newUserJpaRepository.findById(username);
    }

    public List<UserInfo> findAll() {
        return newUserJpaRepository.findAll();
    }
}
