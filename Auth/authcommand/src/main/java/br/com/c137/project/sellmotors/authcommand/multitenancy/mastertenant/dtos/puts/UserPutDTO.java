package br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.puts;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPutDTO(
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Inscription is required")
        String inscription,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Telephone is required")
        String telephone,

        String cellPhone
) {
}
