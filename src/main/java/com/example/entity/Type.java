package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="type")
public class Type {

    @Id
    @GeneratedValue
    @Column(name="type_id")
    private int typeId;

    @Column(name="type_name")
    private String typeName;


}
