package br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.posts;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserPostDTO(
        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Inscription is required")
        @Pattern(regexp = "\\d+", message = "Inscription must contain only numbers")
        String inscription,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
                message = "Password must contain at least one uppercase, one lowercase, one number and one special character"
        )
        String password,

        @NotBlank(message = "Telephone is required")
        String telephone,

        String cellPhone
) {
}
