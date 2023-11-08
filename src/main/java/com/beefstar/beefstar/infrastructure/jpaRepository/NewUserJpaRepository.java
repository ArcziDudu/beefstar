package com.beefstar.beefstar.infrastructure.jpaRepository;

import com.beefstar.beefstar.infrastructure.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewUserJpaRepository extends JpaRepository<UserInfo, String> {
}
