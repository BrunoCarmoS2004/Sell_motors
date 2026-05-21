package br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models;

public record Location(
        String addressLine,

        String zipCode,

        City city
) {
}
