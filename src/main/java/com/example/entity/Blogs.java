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
//public class Blogs {
//
//	@Id
//	@GeneratedValue
//	@Column(name = "id")
//	private int id;
//
//	@Column(name = "blog_name")
//	private String name;
//
//	@Lob
//	@Column(name = "background_image2", columnDefinition = "LONGBLOB")
//	private byte[] image2;
//
//	@Lob
//	@Column(name = "background_image1", columnDefinition = "LONGBLOB")
//	private byte[] image1;
//
//	@Lob
//	@Column(name = "background_image3", columnDefinition = "LONGBLOB")
//	private byte[] image3;
//
//	@Lob
//	@Column(name = "background_image4", columnDefinition = "LONGBLOB")
//	private byte[] image4;
//
//	@Lob
//	@Column(name = "background_image5", columnDefinition = "LONGBLOB")
//	private byte[] image5;
//
//	@Column(name = "blog_content", columnDefinition = "LONGTEXT")
//	private String text;
//
//}
