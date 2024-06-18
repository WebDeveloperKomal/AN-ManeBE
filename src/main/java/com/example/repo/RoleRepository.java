package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
