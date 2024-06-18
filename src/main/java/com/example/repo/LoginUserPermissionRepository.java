package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.LoginUserDetails;

public interface LoginUserPermissionRepository extends JpaRepository<LoginUserDetails, Integer>{

}
