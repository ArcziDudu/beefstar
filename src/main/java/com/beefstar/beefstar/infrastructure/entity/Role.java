package com.beefstar.beefstar.infrastructure.entity;



import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

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
