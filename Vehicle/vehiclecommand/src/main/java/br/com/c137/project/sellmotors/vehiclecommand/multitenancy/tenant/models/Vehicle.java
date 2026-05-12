package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models;

import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.enums.VehicleStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.c137.project.sellmotors.vehiclecommand.utils.ServiceUtils.getUserIdFromToken;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 120)
    private String marca;

    @Column(nullable = false, length = 120)
    private String modelo;

    @Column(nullable = false, length = 120)
    private String versao;

    @Column(name = "ano_modelo", nullable = false)
    private Integer anoModelo;

    @Column(name = "ano_fabricacao", nullable = false)
    private Integer anoFabricacao;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer quilometragem;

    @Column(name = "tipo_combustivel", nullable = false, length = 30)
    private String tipoCombustivel;

    @Column(name = "tipo_transmissao", nullable = false, length = 30)
    private String tipoTransmissao;

    @Column(nullable = false, unique = true, length = 17)
    private String chassi;

    @Column(nullable = false, unique = true, length = 10)
    private String placa;

    @Column(nullable = false, length = 30, name = "status_veiculo")
    @Enumerated(EnumType.STRING)
    private VehicleStatus statusVeiculo;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private UUID createdBy;

    @Column(name = "entity_status")
    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    @PrePersist
    public void onCreate() {
        this.createdBy = getUserIdFromToken();
        this.entityStatus = EntityStatus.ATIVO;
    }
}
