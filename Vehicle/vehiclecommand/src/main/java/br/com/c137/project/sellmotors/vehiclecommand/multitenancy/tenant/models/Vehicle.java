package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models;

import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.enums.VehicleStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static br.com.c137.project.sellmotors.vehiclecommand.utils.ServiceUtils.getUserIdFromToken;

@Data
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "category_id")
    private String categoryId;

    private BigDecimal price;

    @Column(name = "currency_id")
    private String currencyId;

    @Column(name = "listing_type_id")
    private String listingTypeId;

    @Column(name = "available_quantity")
    private Integer availableQuantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_veiculo")
    private VehicleStatus statusVeiculo;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", name = "channels")
    private List<String> channels;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", name = "attributes")
    private List<AnuncioAttribute> attributes;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", name = "locations")
    private Location locations;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", name = "pictures")
    private List<Picture> pictures;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_status")
    private EntityStatus entityStatus;

    @Column(name = "created_by")
    private UUID createdBy;

    @PrePersist
    public void onCreate() {
        this.createdBy = getUserIdFromToken();
        this.entityStatus = EntityStatus.ATIVO;
    }
}