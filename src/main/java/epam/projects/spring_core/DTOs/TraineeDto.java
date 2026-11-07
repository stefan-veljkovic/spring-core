package epam.projects.spring_core.DTOs;

import jakarta.validation.constraints.NotBlank;

public record TraineeDto(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String address) {}
