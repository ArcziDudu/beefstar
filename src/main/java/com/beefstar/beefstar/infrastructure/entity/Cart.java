package com.beefstar.beefstar.infrastructure.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor // Add this annotation
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;
    @OneToOne
    private  Product product;
    @OneToOne(fetch = FetchType.LAZY)
    private UserInfo userInfo;

}
