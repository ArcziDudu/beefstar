package com.beefstar.beefstar.infrastructure.jpaRepository;

import com.beefstar.beefstar.infrastructure.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, String> {
}
