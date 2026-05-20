package br.com.c137.project.sellmotors.sync.dtos.auxiliary.vehicle;

public record Location(
        String addressLine,

        String zipCode,

        City city
) {
}
