package com.example.entity;

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
public class ServicesContent {
	
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name = "serv_title" , columnDefinition = "LONGTEXT")
    private String title;
    
    @Column(name = "info_text" , columnDefinition = "LONGTEXT")
    private String information;
    
}
