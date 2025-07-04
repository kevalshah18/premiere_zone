package com.pl.premiere_zone.player.controller;

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
    public List<Player> getPlayers(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String nation) {
        if (team != null && position != null) {
            return playerService.getPlayersFromTeamAndPos(team, position);
        }else if (name!=null){
            return playerService.getPlayersByName(name);
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
    public ResponseEntity<Player> addPlayer(@RequestBody @Valid Player player){
        Player createdPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player){
        Player resultPlayer = playerService.updatePlayer(player);
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
