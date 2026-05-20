package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.models;

import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.enums.EntityStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.c137.project.sellmotors.vehiclecommand.utils.ServiceUtils.getUserIdFromToken;

public record Location(
        String addressLine,

        String zipCode,

        City city
) {
}
