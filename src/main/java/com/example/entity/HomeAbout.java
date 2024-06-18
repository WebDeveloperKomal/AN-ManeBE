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
//public class HomeAbout {
//
//	
//	@Id
//	@GeneratedValue
//	@Column(name = "id")
//	private int id;
//
//	@Column(name = "home_about")
//	private String about;
//
//	@Column(name = "abo_title" , columnDefinition = "LONGTEXT")
//	private String about_title;
//
//	@Column(name = "about_text" , columnDefinition = "LONGTEXT")
//	private String about_text;
//	
//    @Lob
//    @Column(name = "about_image", columnDefinition = "LONGBLOB")
//    private byte[] image_a;
//
//}
