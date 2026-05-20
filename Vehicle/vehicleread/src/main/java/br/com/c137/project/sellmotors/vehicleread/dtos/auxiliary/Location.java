package br.com.c137.project.sellmotors.vehicleread.dtos.auxiliary;

public record Location(
        String addressLine,

        String zipCode,

        City city
) {
}
