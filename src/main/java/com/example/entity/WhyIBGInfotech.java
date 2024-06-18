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
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class WhyIBGInfotech {
//
//	
//    @Id
//    @GeneratedValue
//    @Column(name="id")
//    private int id;
//
//    @Column(name = "why_infotech")
//    private String name;
//
//    @Column(name = "heading")
//    private String title;
//    
//    @Lob
//    @Column(name = "description", columnDefinition = "LONGTEXT")
//    private String description;
//
//    @Lob
//    @Column(name = "emp_image", columnDefinition = "LONGBLOB")
//    private byte[] image;
//    
//}
