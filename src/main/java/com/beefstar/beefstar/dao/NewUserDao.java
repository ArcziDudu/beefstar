package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.domain.NewUserDTO;
import com.beefstar.beefstar.infrastructure.entity.NewUser;

public interface NewUserDao {
    NewUser save(NewUserDTO newUser);
}
