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
//public class Achievement {
//
//	    @Id
//	    @GeneratedValue
//	    @Column(name="id")
//	    private int id;
//
//	    @Column(name = "achievement_year")
//	    private String year;
//
//	    @Lob
//	    @Column(name = "achievement_image", columnDefinition = "LONGBLOB")
//	    private byte[] image;
//	    
//	    @Column(name = "title")
//	    private String heading;
//	    
//	    @Column(name = "description" , columnDefinition = "LONGTEXT")
//	    private String text;
//}
