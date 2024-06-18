package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.entity.SubscribeEmail;

public interface SubscribeEmailRepository extends JpaRepository<SubscribeEmail, Integer>{
	

	  @Query("SELECT COUNT(c) FROM SubscribeEmail c")
	  Long getTotalSubscribedMemberCount();

}
