package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.RolePermissionType;

import jakarta.transaction.Transactional;

public interface RolePermissionTypeRepository extends JpaRepository<RolePermissionType, Integer> {

	@Query("SELECT rpt FROM RolePermissionType rpt WHERE rpt.role.id = :roleId")
	List<RolePermissionType> findByRoleId(@Param("roleId") int roleId);

	@Modifying
	@Transactional
	@Query("DELETE FROM RolePermissionType rpt WHERE rpt.role.id = :roleId")
	void deleteRolePermissionTypeByRoleId(@Param("roleId") int roleId);
}
