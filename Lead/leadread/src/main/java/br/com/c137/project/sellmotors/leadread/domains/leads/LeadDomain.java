package br.com.c137.project.sellmotors.leadread.domains.leads;

import br.com.c137.project.sellmotors.leadread.enums.EntityStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "leadDto")
@TypeAlias("br.com.c137.project.sellmotors.sync.dtos.LeadDto")
public class LeadDomain {

    @Id
    private UUID id;

    private String fullName;

    private String email;

    private String phone;

    private String document;

    private EntityStatus entityStatus;

    private LocalDateTime deletedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UUID createdBy;
}