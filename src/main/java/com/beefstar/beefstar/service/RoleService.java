package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.RoleDao;
import com.beefstar.beefstar.domain.RoleDTO;
import com.beefstar.beefstar.infrastructure.entity.Role;
import com.beefstar.beefstar.infrastructure.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(RoleDTO role) {
       return roleDao.save(role);
    }
}
