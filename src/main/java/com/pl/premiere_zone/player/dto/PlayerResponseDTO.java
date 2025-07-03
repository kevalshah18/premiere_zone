package com.pl.premiere_zone.player.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;


@Builder
public record PlayerResponseDTO(
        UUID id,
        String name,
        String nation,
        String pos,
        String team,
        int age,
        Instant createdAt,
        Instant updatedAt
) {
}
