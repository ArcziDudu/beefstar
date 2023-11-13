package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.RoleDao;
import com.beefstar.beefstar.dao.UserInfoDao;
import com.beefstar.beefstar.domain.RoleDTO;
import com.beefstar.beefstar.domain.UserInfoDTO;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import com.beefstar.beefstar.infrastructure.mapper.NewUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private NewUserMapper newUserMapper;

    public UserInfo registerNewUser(UserInfoDTO newUser) {
        RoleDTO role = roleDao.findById("User").orElseThrow(()->new UsernameNotFoundException("user not found"));;
        return userInfoDao.save(UserInfoDTO.builder()
                .userName(newUser.userName())
                .userFirstName(newUser.userFirstName())
                .userLastName(newUser.userLastName())
                .role(Set.of(role))
                .userPassword(passwordEncoder.encode(newUser.userPassword()))
                .build());
    }

    public void initRolesAndUser() {
        RoleDTO adminRole = RoleDTO.builder()
                .roleName("Admin")
                .roleDescription("Admin role")
                .build();
        RoleDTO userRole = RoleDTO.builder()
                .roleName("User")
                .roleDescription("Default role for new record")
                .build();

        roleDao.save(adminRole);
        roleDao.save(userRole);

        UserInfoDTO adminUser = UserInfoDTO.builder()
                .userFirstName("Admin")
                .userLastName("Admin")
                .userName("AdminBeefStar")
                .userPassword(passwordEncoder.encode("admin@pass"))
                .role(Set.of(adminRole))
                .build();

        UserInfoDTO user = UserInfoDTO.builder()
                .userFirstName("Arczi")
                .userLastName("Augustyn")
                .userName("ArcziDudu")
                .userPassword(passwordEncoder.encode("admin@pass"))
                .role(Set.of(userRole))
                .build();

        userInfoDao.save(adminUser);
        userInfoDao.save(user);
    }

}