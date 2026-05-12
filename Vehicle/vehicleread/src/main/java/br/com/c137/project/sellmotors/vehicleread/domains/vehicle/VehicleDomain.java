package br.com.c137.project.sellmotors.vehicleread.domains.vehicle;

import br.com.c137.project.sellmotors.vehicleread.enums.EntityStatus;
import br.com.c137.project.sellmotors.vehicleread.enums.VehicleStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "vehicleDto")
@TypeAlias("br.com.c137.project.sellmotors.sync.dtos.VehicleDto")
public class VehicleDomain {
    @Id
    private UUID id;

    private String marca;

    private String modelo;

    private String versao;

    private Integer anoModelo;

    private Integer anoFabricacao;

    private BigDecimal preco;

    private Integer quilometragem;

    private String tipoCombustivel;

    private String tipoTransmissao;

    private String chassi;

    private String placa;

    private VehicleStatus statusVeiculo;

    private LocalDateTime deletedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private EntityStatus entityStatus;

    private UUID createdBy;
}
