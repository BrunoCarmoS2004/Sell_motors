package br.com.c137.project.sellmotors.vehicleread.dtos;

import br.com.c137.project.sellmotors.vehicleread.enums.EntityStatus;
import br.com.c137.project.sellmotors.vehicleread.enums.VehicleStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record VehicleFullDTO(
        UUID id,

        String marca,

        String modelo,

        String versao,

        Integer anoModelo,

        Integer anoFabricacao,

        BigDecimal preco,

        Integer quilometragem,

        String tipoCombustivel,

        String tipoTransmissao,

        String chassi,

        String placa,

        VehicleStatus statusVeiculo,

        LocalDateTime deletedAt,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        EntityStatus entityStatus,

        UUID createdBy
) {
}
