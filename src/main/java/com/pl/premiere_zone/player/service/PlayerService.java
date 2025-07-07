package com.pl.premiere_zone.player.service;

import com.pl.premiere_zone.player.dto.PlayerRequestDTO;
import com.pl.premiere_zone.player.dto.PlayerResponseDTO;
import com.pl.premiere_zone.player.entity.Player;
import com.pl.premiere_zone.player.mapper.PlayerMapper;
import com.pl.premiere_zone.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper mapper;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, PlayerMapper mapper) {
        this.playerRepository = playerRepository;
        this.mapper = mapper;
    }

    public List<PlayerResponseDTO> getPlayers() {
        return playerRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public List<PlayerResponseDTO> getPlayersFromTeam(String teamName) {
        return playerRepository.findAll().stream().filter(player -> player.getTeam().equals(teamName)).map(mapper::toDto).collect(Collectors.toList());
    }

    public List<PlayerResponseDTO> getPlayersByName(String name) {
        return playerRepository.findAll().stream().filter(player -> player.getPlayer().toLowerCase().contains(name.toLowerCase())).map(mapper::toDto).collect(Collectors.toList());

    }

    public List<PlayerResponseDTO> getPlayersFromPos(String pos) {
        return playerRepository.findAll().stream().filter(player -> player.getPos().equalsIgnoreCase(pos)).map(mapper::toDto).collect(Collectors.toList());
    }

    public List<PlayerResponseDTO> getPlayersFromNation(String nation) {
        return playerRepository.findAll().stream().filter(player -> player.getNation().equalsIgnoreCase(nation.toLowerCase())).map(mapper::toDto).collect(Collectors.toList());
    }

    public List<PlayerResponseDTO> getPlayersFromTeamAndPos(String nation, String pos) {
        return playerRepository.findAll().stream().filter(player -> player.getNation().equalsIgnoreCase(nation) && player.getPos().equalsIgnoreCase(pos)).map(mapper::toDto).collect(Collectors.toList());
    }

    public PlayerResponseDTO addPlayer(PlayerRequestDTO player) {
        Player playerEntity = mapper.toEntity(player);
        playerRepository.save(playerEntity);
        return mapper.toDto(playerEntity);
    }

    public PlayerResponseDTO updatePlayer(UUID id, PlayerRequestDTO updatedPlayer) {
        Player existingPlayer = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No player with ID " + id));
        mapper.updateEntity(updatedPlayer, existingPlayer);
        Player playerToUpdate = mapper.toEntity(updatedPlayer);


        playerRepository.save(playerToUpdate);
        return mapper.toDto(playerToUpdate);


    }

    @Transactional
    public void deletePlayer(String name) {
        playerRepository.deleteByPlayer(name);
    }

    @Transactional
    public void deletePlayer(UUID id) {
        if (!playerRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No player with ID:" + id);
        }
        playerRepository.deleteById(id);
    }
}
