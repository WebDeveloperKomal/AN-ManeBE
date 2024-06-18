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
//public class HomeFooter {
//	
//
//    @Id
//    @GeneratedValue
//    @Column(name="id")
//    private int id;
//
//    @Column(name = "ping_now")
//    private String ping_now;
//
//    @Column(name = "text" , columnDefinition = "LONGTEXT")
//    private String text;
//
//    @Lob
//    @Column(name = "ibg_logo", columnDefinition = "LONGBLOB")
//    private byte[] image;
//
//    @Column(name = "office_hours")
//    private String open_hours;
//    
//    @Column(name = "footer_title" , columnDefinition = "LONGTEXT")
//    private String footer_title;
//}
