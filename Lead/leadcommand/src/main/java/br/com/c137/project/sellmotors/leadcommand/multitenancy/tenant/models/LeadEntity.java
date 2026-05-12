package br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.models;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.enums.EntityStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.c137.project.sellmotors.leadcommand.utils.ServiceUtils.getUserIdFromToken;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "leads")
public class LeadEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column
    private String document;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable = false, name = "entity_status")
    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    @Column(name = "created_by")
    private UUID createdBy;

    @PrePersist
    private void onCreate() {
        this.createdBy = getUserIdFromToken();
        this.entityStatus = EntityStatus.ATIVO;
    }

}
