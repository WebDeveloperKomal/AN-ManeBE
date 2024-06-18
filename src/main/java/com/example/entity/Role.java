package com.example.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@Column(name = "role_name")
	private String name;
	
    @Column(name="is_active",nullable=false)
    private boolean isActive;

    @Column(name="from_date",nullable=false)
    private Date fromDate=new Date();

    @Column(name="to_date",nullable=false)
    private Date toDate=new Date();
    
}
