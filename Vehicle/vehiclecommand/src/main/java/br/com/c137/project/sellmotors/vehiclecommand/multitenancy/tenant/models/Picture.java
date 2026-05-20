package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;
}