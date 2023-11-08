package com.beefstar.beefstar.infrastructure.JpaImpl;

import com.beefstar.beefstar.dao.NewUserDao;
import com.beefstar.beefstar.domain.NewUserDTO;
import com.beefstar.beefstar.infrastructure.entity.NewUser;
import com.beefstar.beefstar.infrastructure.jpaRepository.NewUserJpaRepository;
import com.beefstar.beefstar.infrastructure.mapper.NewUserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class NewUserImpl implements NewUserDao {
    private NewUserJpaRepository newUserJpaRepository;
    private NewUserMapper newUserMapper;


    @Override
    public NewUser save(NewUserDTO newUser) {
        return newUserJpaRepository.save(newUserMapper.mapFromDto(newUser));
    }
}
