package br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.models;

import br.com.c137.project.sellmotors.leadcommand.multitenancy.tenant.enums.EntityStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "leads")
public class LeadEntity extends BaseEntity{

    @Column(nullable = false, name = "full_name")
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column
    private String document;

    @PrePersist
    private void onCreate() {
        setEntityStatus(EntityStatus.ATIVO);
    }

}
