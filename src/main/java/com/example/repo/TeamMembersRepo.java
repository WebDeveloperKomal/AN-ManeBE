package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.ContactUs;
import com.example.entity.TeamMembers;

public interface TeamMembersRepo extends JpaRepository<TeamMembers, Integer>{

	
	@Query("SELECT t FROM TeamMembers t WHERE t.status = 0")
	List<TeamMembers> findAllByStatusIsZero();
	
	@Query("SELECT COUNT(t) FROM TeamMembers t WHERE t.status = 0")
	Long getTotalMemberCount();
	
	 @Query("SELECT t FROM TeamMembers t WHERE t.id = :id AND t.status = 0")
		public TeamMembers  findById(@Param("id")int id);
		
		@Query("SELECT t FROM TeamMembers t WHERE t.status = 0")
		public List<TeamMembers>  getAll();
		
//		  @Query("DELETE aw FROM ContactUs aw WHERE aw.id = :id AND aw.isDeleted = false")
		TeamMembers deleteById(@Param("id") int id);
}
