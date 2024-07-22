package com.example.msplayer.repository;


import com.example.msplayer.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByTeamId(String teamId);
}
