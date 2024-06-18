package com.example.entity;

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
public class TeamMembers {

	    @Id
	    @GeneratedValue
	    @Column(name="id")
	    private int id;

//	    @Column(name = "page_title")
//	    private String title;

	    @Column(name = "mem_name")
	    private String name;

	    @Lob
	    @Column(name = "emp_image", columnDefinition = "LONGBLOB")
	    private byte[] image;

	    @Column(name = "designation")
	    private String designation;
	    
	    @Column(name = "status")
	    private int status;

}
