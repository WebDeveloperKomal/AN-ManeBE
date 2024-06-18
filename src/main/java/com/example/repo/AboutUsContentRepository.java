package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.AboutUsContent;
import com.example.entity.TeamMembers;

public interface AboutUsContentRepository extends JpaRepository<AboutUsContent, Integer> {

	@Query("SELECT t FROM AboutUsContent t WHERE t.id = :id AND t.isDeleted = false")
	public AboutUsContent findById(@Param("id") int id);

	@Query("SELECT t FROM AboutUsContent t WHERE t.isDeleted = false")
	public List<AboutUsContent> getAll();

//		  @Query("DELETE aw FROM AboutUsContent aw WHERE aw.id = :id AND aw.isDeleted = false")
	AboutUsContent deleteById(@Param("id") int id);

}
