package com.pl.premiere_zone.player.controller;

import com.pl.premiere_zone.player.dto.*;
import com.pl.premiere_zone.player.entity.Player;
import com.pl.premiere_zone.player.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerResponseDTO> getPlayers(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String player,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String nation) {
        if (team != null && position != null) {
            return playerService.getPlayersFromTeamAndPos(team, position);
        }else if (player!=null){
            return playerService.getPlayersByName(player);
        }else if (team != null) {
            return playerService.getPlayersFromTeam(team);
        } else if (position != null) {
            return playerService.getPlayersFromPos(position);
        } else if (nation != null) {
            return playerService.getPlayersFromNation(nation);
        } else {
            return playerService.getPlayers();
        }
    }

    @PostMapping
    public ResponseEntity<PlayerResponseDTO> addPlayer(@RequestBody @Valid PlayerRequestDTO dto){
        return new ResponseEntity<>(playerService.addPlayer(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerResponseDTO> updatePlayer(@PathVariable UUID id ,@Valid@RequestBody PlayerRequestDTO player){
        PlayerResponseDTO resultPlayer = playerService.updatePlayer(id,player);
        if(resultPlayer!=null){
            return new ResponseEntity<>(resultPlayer,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/name/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable String playerName){
        playerService.deletePlayer(playerName);
        return new ResponseEntity<>("Player deleted successfully",HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable UUID id){
        playerService.deletePlayer(id);
        return new ResponseEntity<>("Player deleted successfully",HttpStatus.OK);
    }
}
