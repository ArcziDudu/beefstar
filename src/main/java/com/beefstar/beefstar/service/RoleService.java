package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.RoleDao;
import com.beefstar.beefstar.domain.RoleDTO;
import com.beefstar.beefstar.infrastructure.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleDao roleDao;

    public Role createNewRole(RoleDTO role) {
        return roleDao.save(role);
    }
}
