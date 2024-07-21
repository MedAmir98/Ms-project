package services;

import dto.PlayerAddResponse;
import dto.PlayerDto;

import java.util.List;

public interface IPlayerService {


    PlayerAddResponse addPlayer(PlayerDto playerDto);
    List<PlayerDto> getAllPlayers();
    PlayerDto getPlayerById(Long id);

}
