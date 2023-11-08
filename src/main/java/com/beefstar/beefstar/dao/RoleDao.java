package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.domain.RoleDTO;
import com.beefstar.beefstar.infrastructure.entity.Role;


import java.util.Optional;

public interface RoleDao {
    Role save(RoleDTO role);

    Optional<RoleDTO> findById(String user);
}
