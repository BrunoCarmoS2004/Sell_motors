package br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.models;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.NeighborhoodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_address", catalog = "financial_master")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "street_address", nullable = false)
    private String streetAddress;

    @Column(nullable = false)
    private Integer number;

    @Enumerated(EnumType.STRING)
    @Column(name = "neighborhood_type", nullable = false)
    private NeighborhoodType neighborhoodType;

    @Column(nullable = false)
    private String neighborhood;

    private String complement;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(name = "city_ibge", nullable = false)
    private String cityIbge;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "address_of_id", nullable = false, updatable = false)
    private UUID addressOf;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_status", nullable = false)
    private EntityStatus entityStatus;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.entityStatus = EntityStatus.ACTIVE;
    }
}
