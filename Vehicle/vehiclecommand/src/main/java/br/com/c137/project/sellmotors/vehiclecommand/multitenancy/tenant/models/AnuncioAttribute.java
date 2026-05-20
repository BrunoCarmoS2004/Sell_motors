package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "attributes")
public class AnuncioAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapeia o "id" que vem no JSON (ex: "BRAND", "MODEL")
    @Column(name = "attribute_key")
    private String attributeKey;

    @Column(name = "value_name")
    private String valueName;
}