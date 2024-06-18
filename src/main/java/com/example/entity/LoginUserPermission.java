package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="employee_permission")
public class LoginUserPermission {

    @Id
    @GeneratedValue
    @Column(name="login_user_permission_id")
    private int loginUserId;

    @ManyToOne
    @JoinColumn(name="id",unique=true)
    private LoginUserDetails loginUserDetails;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

}
