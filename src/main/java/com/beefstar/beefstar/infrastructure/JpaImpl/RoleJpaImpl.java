package com.beefstar.beefstar.infrastructure.JpaImpl;

import com.beefstar.beefstar.dao.RoleDao;
import com.beefstar.beefstar.domain.RoleDTO;
import com.beefstar.beefstar.infrastructure.entity.Role;
import com.beefstar.beefstar.infrastructure.jpaRepository.RoleJpaRepository;
import com.beefstar.beefstar.infrastructure.mapper.RoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RoleJpaImpl implements RoleDao {
    private RoleJpaRepository roleJpaRepository;
    private RoleMapper roleMapper;
    @Override
    public Role save(RoleDTO role) {
        return roleJpaRepository.save(roleMapper.mapFromDto(role));
    }

    @Override
    public Optional<RoleDTO> findById(String user) {
     return roleJpaRepository.findById(user).map(roleMapper::mapFromEntity);

    }
}