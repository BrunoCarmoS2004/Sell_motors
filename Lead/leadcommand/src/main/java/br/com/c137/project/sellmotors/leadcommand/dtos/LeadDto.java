package br.com.c137.project.sellmotors.leadcommand.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadDto {
    private String id;

    private String fullName;

    private String email;

    private String phone;

    private String document;
}
