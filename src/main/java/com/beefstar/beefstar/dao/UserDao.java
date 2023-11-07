package com.beefstar.beefstar.dao;

import com.beefstar.beefstar.entity.NewUser;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<NewUser, String> {
}
