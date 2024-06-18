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
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class Gallery {
//	
//	
//	    @Id
//	    @GeneratedValue
//	    @Column(name="id")
//	    private int id;
//
//	    @Column(name = "image_type")
//	    private String title;
//
//	    @Column(name = "image_scenario")
//	    private String title2;
//
//	    @Lob
//	    @Column(name = "footer_image", columnDefinition = "LONGBLOB")
//	    private byte[] upload_image;
//	    
//	    
//	    @Column(name = "status")
//	    private String status;
//	    
//
//}
