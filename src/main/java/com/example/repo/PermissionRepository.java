package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{

}
