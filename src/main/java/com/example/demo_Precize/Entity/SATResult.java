package com.example.demo_Precize.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "SATResult")
public class SATResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String address;
    private String city;
    private String country;
    private String pincode;
    private int score;
    private String result;

}
