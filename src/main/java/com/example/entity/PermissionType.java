package com.example.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="permission_type")
public class
PermissionType {

    @Id
    @GeneratedValue
    @Column(name="permission_type_id")
    private int permissionTypeId;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name="type_id")
    private Type type;

    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name="permission_id")
    private Permission permission;

    @Column(name="permission_type_name")
    private String permissionTypeName;

}
