package com.pl.premiere_zone.player.repository;

import com.pl.premiere_zone.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
    void deleteByPlayer(String player);
    Optional<Player> findByPlayer(String player);

}
