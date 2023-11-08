package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.domain.RoleDTO;
import com.beefstar.beefstar.infrastructure.entity.Role;

public interface RoleDao {
    Role save(RoleDTO role);
}
