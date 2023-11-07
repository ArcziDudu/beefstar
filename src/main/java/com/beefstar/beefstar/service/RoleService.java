package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.RoleDao;
import com.beefstar.beefstar.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
       return roleDao.save(role);
    }
}
