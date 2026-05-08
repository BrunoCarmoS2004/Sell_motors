package br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.dtos.puts;

import br.com.c137.project.sellmotors.vehiclecommand.multitenancy.tenant.enums.VehicleStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record VehiclePutDTO(
        @NotBlank(message = "A marca é obrigatória")
        @Size(max = 120)
        String marca,

        @NotBlank(message = "O modelo é obrigatório")
        @Size(max = 120)
        String modelo,

        @NotBlank(message = "A versão é obrigatória")
        @Size(max = 120)
        String versao,

        @NotNull(message = "O ano do modelo é obrigatório")
        @Min(value = 1800, message = "Ano do modelo inválido")
        Integer anoModelo,

        @NotNull(message = "O ano de fabricação é obrigatório")
        @Min(value = 1800, message = "Ano de fabricação inválido")
        Integer anoFabricacao,

        @NotNull(message = "O preço é obrigatório")
        @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
        @Digits(integer = 17, fraction = 2)
        BigDecimal preco,

        @NotNull(message = "A quilometragem é obrigatória")
        @PositiveOrZero(message = "A quilometragem não pode ser negativa")
        Integer quilometragem,

        @NotBlank(message = "O tipo de combustível é obrigatório")
        String tipoCombustivel,

        @NotBlank(message = "O tipo de transmissão é obrigatório")
        String tipoTransmissao,

        @NotBlank(message = "O chassi (VIN) é obrigatório")
        @Size(min = 17, max = 17, message = "O chassi deve ter exatamente 17 caracteres")
        String chassi,

        @NotBlank(message = "A placa é obrigatória")
        @Pattern(regexp = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$", message = "Placa em formato inválido (Padrão Mercosul ou Antigo)")
        String placa,

        @NotNull(message = "O status do veículo é obrigatório")
        VehicleStatus statusVeiculo
) {
}
