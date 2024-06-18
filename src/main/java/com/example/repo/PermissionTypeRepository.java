package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.PermissionType;

public interface PermissionTypeRepository extends JpaRepository<PermissionType, Integer>{

}
