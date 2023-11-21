package com.beefstar.beefstar.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "image_model")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @Column(length = 50000000)
    private byte[] picByte;


}
