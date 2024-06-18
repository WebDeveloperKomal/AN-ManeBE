package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.AboutUsContent;
import com.example.entity.Testimonials;

public interface TestimonialsRepository extends JpaRepository<Testimonials, Integer>{

	
	@Query("SELECT t FROM Testimonials t WHERE t.id = :id AND t.isDeleted = false")
	public Testimonials findById(@Param("id") int id);

	@Query("SELECT t FROM Testimonials t WHERE t.isDeleted = false")
	public List<Testimonials> getAll();

//		  @Query("DELETE aw FROM AboutUsContent aw WHERE aw.id = :id AND aw.isDeleted = false")
	Testimonials deleteById(@Param("id") int id);
}
