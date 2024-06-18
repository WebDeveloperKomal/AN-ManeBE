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
//public class Services {
//
//	
//    @Id
//    @GeneratedValue
//    @Column(name="id")
//    private int id;
//
//    @Column(name = "service_name")
//    private String name;
//
//    @Column(name = "service_content" , columnDefinition = "LONGTEXT")
//    private String text;
//
//    @Lob
//    @Column(name = "background_image1", columnDefinition = "LONGBLOB")
//    private byte[] image1;
//    
//    @Lob
//    @Column(name = "background_image2", columnDefinition = "LONGBLOB")
//    private byte[] image2;
//    
//    @Column(name = "home_text-added" , columnDefinition = "LONGTEXT")
//    private String home_text;
//   
//}
