package com.pl.premiere_zone.player.mapper;

import com.pl.premiere_zone.player.dto.*;
import com.pl.premiere_zone.player.entity.Player;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface PlayerMapper {

    /* ---------- CREATE ---------- */
    /** Converts request DTO to new entity for POST */
    Player toEntity(PlayerRequestDTO dto);

    /* ---------- UPDATE ---------- */
    /**
     * Updates an existing entity in-place.
     * nullValuePropertyMappingStrategy=IGNORE avoids overwriting fields the client didn't send.
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(PlayerRequestDTO dto, @MappingTarget Player entity);

    /* ---------- READ ---------- */
    /** Converts entity to response DTO for GET */
    PlayerResponseDTO toDto(Player entity);
}