package br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.models;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.CreationStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.DatabaseStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.EntityStatus;
import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.enums.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users", catalog = "financial_master")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String inscription;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String telephone;

    @Column(name = "cell_phone")
    private String cellPhone;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_acess", nullable = false, updatable = false)
    private LocalDateTime lastAcess;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRoles userRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "db_status", nullable = false)
    private DatabaseStatus dbStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "creation_status", nullable = false)
    private CreationStatus creationStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_status", nullable = false)
    private EntityStatus entityStatus;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.lastAcess = LocalDateTime.now();
        this.entityStatus = EntityStatus.ACTIVE;
        this.creationStatus = CreationStatus.REGISTERED_INCOMPLETE;
        this.userRole = UserRoles.COMMUM_USER;
        this.dbStatus = DatabaseStatus.NOT_CREATED;
    }
}
