package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.RoleDao;
import com.beefstar.beefstar.dao.UserInfoDao;
import com.beefstar.beefstar.domain.RoleDTO;
import com.beefstar.beefstar.domain.UserInfoDTO;
import com.beefstar.beefstar.domain.exception.UserNotFoundException;
import com.beefstar.beefstar.domain.exception.UserWithThatUsernameExists;
import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserInfoDao userInfoDao;

    private final RoleDao roleDao;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserInfo registerNewUser(UserInfoDTO newUser) {
        Optional<UserInfo> byId = userInfoDao.findById(newUser.userName());
        if(byId.isPresent()){
            throw new UserWithThatUsernameExists(String.format("User with that username [%s] exists!", newUser.userName()));
        }else {
            RoleDTO role = roleDao.findById("User").orElseThrow(() -> new UsernameNotFoundException("role not found"));
            ;
            return userInfoDao.save(UserInfoDTO.builder()
                    .userName(newUser.userName())
                    .userFirstName(newUser.userFirstName())
                    .userLastName(newUser.userLastName())
                    .role(Set.of(role))
                    .userPassword(passwordEncoder.encode(newUser.userPassword()))
                    .build());
        }

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
        userInfoDao.save(adminUser);
    }

    public UserInfo findUserById(String userId) {
        Optional<UserInfo> byId = userInfoDao.findById(userId);
        if (byId.isEmpty()) {
            throw new UserNotFoundException(String.format("User with id [%s] not found", userId));
        }
        return byId.get();
    }

    public List<UserInfo> findAll() {
        return userInfoDao.findAll();
    }
}
