package com.pl.premiere_zone.player.dto;
import jakarta.validation.constraints.*;


public record PlayerRequestDTO(

        @NotBlank(message = "Name is mandatory")
        String name,

        @NotBlank(message = "Nation is mandatory")
        String nation,

        @NotBlank(message = "Position is mandatory")
        String pos,

        @NotBlank(message = "Team is mandatory")
        String team,

        @Min(value = 16, message = "Age must be at least 16")
        @Max(value = 50, message = "Age must be realistic")
        int age
) { }