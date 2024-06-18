package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {

}
