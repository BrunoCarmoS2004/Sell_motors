package br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.posts;

import jakarta.validation.constraints.*;

public record UserLoginPostDTO(
        @Email(message = "Invalid email format")
        @NotNull
        String email,
        @Pattern(regexp = "\\d+", message = "Inscription must contain only numbers")
        @NotNull
        String inscription,
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
) {

}
