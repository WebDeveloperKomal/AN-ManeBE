//package com.example.entity;
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
//public class Careers {
//
//	@Id
//	@GeneratedValue
//	@Column(name = "id")
//	private int id;
//
//	@Column(name = "post_name")
//	private String post;
//
//	@Column(name = "content", columnDefinition = "LONGTEXT")
//	private String post_content;
//
//	@Lob
//	@Column(name = "background_image", columnDefinition = "LONGBLOB")
//	private byte[] image3;
//}
