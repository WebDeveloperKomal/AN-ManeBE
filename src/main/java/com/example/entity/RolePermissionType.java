package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="role_permission_type")
public class RolePermissionType {

    @Id
    @GeneratedValue
    @Column(name="role_permission_type_id")
    private int rolePermissionTypeId;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name="role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name="permission_type_id")
    private PermissionType permissionType;

}
