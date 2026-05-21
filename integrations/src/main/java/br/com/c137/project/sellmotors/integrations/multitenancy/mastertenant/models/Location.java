package br.com.c137.project.sellmotors.integrations.multitenancy.mastertenant.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Location(
        String addressLine,

        String zipCode,

        City city
) {
}
