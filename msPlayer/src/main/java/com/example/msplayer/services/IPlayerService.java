package com.example.msplayer.services;



import com.example.msplayer.dto.PlayerAddResponse;
import com.example.msplayer.dto.PlayerDto;

import java.util.List;

public interface IPlayerService {


    PlayerAddResponse addPlayer(PlayerDto playerDto);
    PlayerAddResponse addAsyncPlayer(PlayerDto playerDto);
    List<PlayerDto> getAllPlayers();
    PlayerDto getPlayerById(Long id);

}
