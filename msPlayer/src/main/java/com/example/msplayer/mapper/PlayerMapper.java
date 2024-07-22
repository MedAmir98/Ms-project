package com.example.msplayer.mapper;


import com.example.msplayer.dto.PlayerDto;
import com.example.msplayer.entities.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    Player playerDtoToPlayer(PlayerDto playerDto);

    PlayerDto playerToPlayerDto(Player player);
}
