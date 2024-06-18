package com.example.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AboutUsContent {
	
	    @Id
	    @GeneratedValue
	    @Column(name="id")
	    private int id;
	    
	    @Column(name = "completed_p_count")
	    private String project;
	    
	    @Column(name = "team_member_count")
	    private String member_count; 
	    
	    @Column(name = "client_review")
	    private String client_review;
	    	    
	    @Column(name = "award_count")
	    private String awards_count;
	    
	    @Column(name = "is_deleted", nullable = false)
		private boolean isDeleted;

		@Column(name = "insert_time", updatable = false)
		@CreationTimestamp
		private Date insertTime = new Date(id);

		@Column(name = "update_time", insertable = false)
		@UpdateTimestamp
		private Date updateTime;
	   
//
//	    @Column(name = "heading_about_us")
//	    private String heading;
//
//	    @Column(name = "text_head" , columnDefinition = "LONGTEXT")
//	    private String text1;
//	    
//	    @Column(name = "text_head2" , columnDefinition = "LONGTEXT")
//	    private String text2;
//	    
//	    @Column(name = "our_mission" , columnDefinition = "LONGTEXT")
//	    private String mission;
//	    
//	    @Column(name = "our_vision" , columnDefinition = "LONGTEXT")
//	    private String vision;
//	    
//	    @Column(name = "our_value" , columnDefinition = "LONGTEXT")
//	    private String value;
////	    
//	    @Column(name = "experience_value")
//	    private String experience;
//	    
//	    @Column(name = "award_text" , columnDefinition = "LONGTEXT")
//	    private String award_text;
//
//	    @Lob
//	    @Column(name = "about_image", columnDefinition = "LONGBLOB")
//	    private byte[] image;
//	    	   
//	    @Column(name = "terminal_text" , columnDefinition = "LONGTEXT")
//	    private String terminal;
//
}
