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
public class Testimonials {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "testimo_name")
	private String test_name;

	@Column(name = "address")
	private String address;

	@Column(name = "add_comment", columnDefinition = "LONGTEXT")
	private String comment;

	@Column(name = "status")
	private int status;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Column(name = "insert_time", updatable = false)
	@CreationTimestamp
	private Date insertTime = new Date(id);

	@Column(name = "update_time", insertable = false)
	@UpdateTimestamp
	private Date updateTime;

}
