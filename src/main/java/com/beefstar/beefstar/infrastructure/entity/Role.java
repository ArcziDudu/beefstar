package com.beefstar.beefstar.infrastructure.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    private String roleName;
    private String roleDescription;


}
