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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactUs {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "user_name")
	private String name;

	@Column(name = "mobile_number")
	private String phone;

	@Column(name = "email_id")
	private String email;

	@Column(name = "message")
	private String message;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Column(name = "insert_time", updatable = false)
	@CreationTimestamp
	private Date insertTime = new Date(id);

	@Column(name = "update_time", insertable = false)
	@UpdateTimestamp
	private Date updateTime;

}
