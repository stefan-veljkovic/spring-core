package epam.projects.spring_core.DTOs;

import jakarta.validation.constraints.NotBlank;

public record TrainerDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String specialization) {}
