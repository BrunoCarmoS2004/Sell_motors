package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_line")
    private String addressLine;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city_id")
    private String cityId;
}
