package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

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

}
