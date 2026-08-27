package com.Shlok.Booking_System.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "resources")
@Data
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}
