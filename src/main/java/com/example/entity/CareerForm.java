//package com.example.entity;
//
//import java.util.Date;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.Lob;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class CareerForm {
//
//	@Id
//	@GeneratedValue
//	@Column(name = "id")
//	private int id;
//	
//	@Column(name = "applicant_name")
//	private String name;
//	
//	@Column(name = "appli_email")
//	private String email;
//	
//	@Column(name = "address")
//	private String address;
//	
//	@Column(name = "dob")
//	private Date dob;
//	
//	@Column(name = "appli_education")
//	private String education;
//	
//	@Column(name = "description", columnDefinition = "LONGTEXT")
//	private String description;
//	
//	@Column(name = "applied_for")
//	private String position;
//	
//	@Column(name = "phone_number")
//	private String phone_number;
//	
//	@Lob
//	@Column(name = "resume_document", columnDefinition = "LONGBLOB")
//	private byte[] resume;
//	
//	@Lob
//	@Column(name = "cover_letter", columnDefinition = "LONGBLOB")
//	private byte[] covr_letter;
//}
