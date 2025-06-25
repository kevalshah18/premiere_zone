package com.pl.premiere_zone.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository){
        this.playerRepository=playerRepository;
    }
    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }
    public List<Player>getPlayersFromTeam(String teamName){
        return playerRepository.findAll().stream().filter(player->player.getTeam().equals(teamName)).collect(Collectors.toList());
    }
    public List<Player>getPlayersByName(String name){
        return playerRepository.findAll().stream().filter(player -> player.getPlayer().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

    }
    public List<Player> getPlayersByPos(String pos){
        return playerRepository.findAll().stream().filter(player-> player.getPos().equalsIgnoreCase(pos))
                .collect(Collectors.toList());
    }
    public List<Player> getPlayersByNation(String nation){
        return playerRepository.findAll().stream().filter(player->player.getNation().equalsIgnoreCase(nation.toLowerCase()))
                .collect(Collectors.toList());
    }
    public List<Player> getPlayersByTeamAndPos(String nation,String pos ){
        return playerRepository.findAll().stream().filter(player -> player.getNation().equalsIgnoreCase(nation) && player.getPos().equalsIgnoreCase(pos))
                .collect(Collectors.toList());
    }
    public Player addPlayer(Player player){
        playerRepository.save(player);
        return player;
    }
    public Player updatePlayer(Player updatedPlayer){
        Optional<Player> existingPlayer = playerRepository.findByPlayer(updatedPlayer.getPlayer());
        if(existingPlayer.isPresent()){
            Player playerToUpdate = existingPlayer.get();

            playerToUpdate.setPlayer((updatedPlayer.getPlayer()));
            playerToUpdate.setNation((updatedPlayer.getNation()));
            playerToUpdate.setPos((updatedPlayer.getPos()));
            playerToUpdate.setAge(updatedPlayer.getAge());
            playerToUpdate.setMp(updatedPlayer.getMp());
            playerToUpdate.setStarts(updatedPlayer.getStarts());
            playerToUpdate.setMin(updatedPlayer.getMin());
            playerToUpdate.setGls(updatedPlayer.getGls());
            playerToUpdate.setAst(updatedPlayer.getAst());
            playerToUpdate.setPk(updatedPlayer.getPk());
            playerToUpdate.setCrdY(updatedPlayer.getCrdY());
            playerToUpdate.setCrdR(updatedPlayer.getCrdR());
            playerToUpdate.setXG(updatedPlayer.getXG());
            playerToUpdate.setXAG(updatedPlayer.getXAG());
            playerToUpdate.setTeam((updatedPlayer.getTeam()));

            playerRepository.save(playerToUpdate);
            return playerToUpdate;

        }else{
            return null;
        }
    }
    @Transactional
    public void deletePlayer(String name){
        playerRepository.deleteByPlayer(name);
    }
}
