package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.UserDao;
import com.beefstar.beefstar.entity.NewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public NewUser registerNewUser(NewUser newUser) {
        return userDao.save(newUser);
    }
}
