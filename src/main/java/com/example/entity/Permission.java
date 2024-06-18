package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="permission")
public class Permission {

    @Id
    @GeneratedValue
    @Column(name="permission_id")
    private int permissionId;

    @Column(name="permission_name")
    private String permissionName;


}
