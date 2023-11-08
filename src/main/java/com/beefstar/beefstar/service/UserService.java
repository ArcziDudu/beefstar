package com.beefstar.beefstar.service;

import com.beefstar.beefstar.dao.RoleDao;
import com.beefstar.beefstar.dao.NewUserDao;
import com.beefstar.beefstar.domain.NewUserDTO;
import com.beefstar.beefstar.domain.RoleDTO;
import com.beefstar.beefstar.infrastructure.entity.NewUser;
import com.beefstar.beefstar.infrastructure.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    private NewUserDao newUserDao;
    private RoleDao roleDao;

    public NewUser registerNewUser(NewUserDTO newUser) {
        return newUserDao.save(newUser);
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

      NewUserDTO adminUser = NewUserDTO.builder()
              .userFirstName("Admin")
              .userLastName("Admin")
              .userName("AdminBeefStar")
              .userPassword("admin@pass")
              .role(Set.of(adminRole))
              .build();

      NewUserDTO user = NewUserDTO.builder()
              .userFirstName("Arczi")
              .userLastName("Augustyn")
              .userName("ArcziDudu")
              .userPassword("admin@pass")
              .role(Set.of(userRole))
              .build();

      newUserDao.save(adminUser);
      newUserDao.save(user);
    }
}
